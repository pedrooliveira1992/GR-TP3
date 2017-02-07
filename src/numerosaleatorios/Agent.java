package numerosaleatorios;

import org.snmp4j.agent.*;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.agent.mo.MOTableRow;
import org.snmp4j.agent.mo.snmp.*;
import org.snmp4j.agent.security.MutableVACM;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModel;
import org.snmp4j.security.USM;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author cruz
 */
public class Agent extends BaseAgent
{
    private String community;
    private int frequency;
    private int table_entries;
    private int hexa_size;
    private String seed_path;
    private String reset_password;
    private String address;
    private Map<Integer,ArrayList<Integer>> matrix; // check

    final OID oidUnpredictableRefresh =
            new OID(new int[] { 1,3,6,1,3,4,1,1,1,0 });
    final OID oidUnpredictableEntradas =
            new OID(new int[] { 1,3,6,1,3,4,1,1,2,0 });
    final OID oidUnpredictableDigitos =
            new OID(new int[] { 1,3,6,1,3,4,1,1,3,0 });
    final OID oidUnpredictableAuthorization =
            new OID(new int[] { 1,3,6,1,3,4,1,1,4,0 });


    public Agent(String address, String com, int freq, int entries, int hex, String path, String pwd) throws IOException
    {
        super(new File("conf.agent"), new File("bootCounter.agent"), new CommandProcessor(new OctetString(MPv3.createLocalEngineID())));
        this.address        = address;
        this.community      = com;
        this.frequency      = freq;
        this.table_entries  = entries;
        this.hexa_size      = hex;
        this.seed_path      = path;
        this.reset_password = pwd;

        MOScalar refresh = MOScalarMaker.CR_RO(oidUnpredictableRefresh,       this.frequency);
        MOScalar linhas  = MOScalarMaker.CR_RO(oidUnpredictableEntradas,      this.table_entries);
        MOScalar n_elems = MOScalarMaker.CR_RO(oidUnpredictableDigitos,       this.hexa_size);
        MOScalar reset   = MOScalarMaker.CR_WO(oidUnpredictableAuthorization, this.reset_password);

        try
        {
            this.registerManagedObjects(refresh, this.community);
            this.registerManagedObjects(linhas,  this.community);
            this.registerManagedObjects(n_elems, this.community);
            this.registerManagedObjects(reset,   this.community);
        }
        catch (DuplicateRegistrationException e)
        {
            e.printStackTrace();
        }
    }

    public Agent() {
        super(new File("conf.agent"), new File("bootCounter.agent"), new CommandProcessor(new OctetString(MPv3.createLocalEngineID())));
    }

    public String getReset_password()
    {
        return this.reset_password;
    }

    public void setReset_password(String pwd)
    {
        this.reset_password = pwd;
    }

    protected void registerManagedObjects(ManagedObject m, String com) throws DuplicateRegistrationException
    {
        server.register(m,null);
    }

    public void registerManagedObject(ManagedObject mo) throws DuplicateRegistrationException {
        server.register(mo,null);
    }

    @Override
    protected void registerManagedObjects() {

    }

    @Override
    protected void unregisterManagedObjects()
    {

    }

    @Override
    protected void addUsmUser(USM usm)
    {

    }

    @Override
    protected void addNotificationTargets(SnmpTargetMIB snmpTargetMIB, SnmpNotificationMIB snmpNotificationMIB)
    {

    }

    /**
     * Adds initial VACM configuration.
     * copy da net
     */
    protected void addViews(VacmMIB vacm) {
        vacm.addGroup(SecurityModel.SECURITY_MODEL_SNMPv2c, new OctetString(
                        "cpublic"), new OctetString("v1v2group"),
                StorageType.nonVolatile);
        vacm.addAccess(new OctetString("v1v2group"), new OctetString(this.community),
                SecurityModel.SECURITY_MODEL_ANY, SecurityLevel.NOAUTH_NOPRIV,
                MutableVACM.VACM_MATCH_EXACT, new OctetString("fullReadView"),
        new OctetString("fullWriteView"), new OctetString(
                "fullNotifyView"), StorageType.nonVolatile);
        vacm.addViewTreeFamily(new OctetString("fullReadView"), new OID("1.3"),
        new OctetString(), VacmMIB.vacmViewIncluded,
        StorageType.nonVolatile);
    }


    protected void addCommunities(SnmpCommunityMIB communityMIB) {
        Variable[] com2sec = new Variable[] {
                new OctetString(this.community), // community name
                new OctetString("c"+this.community), // security name
                getAgent().getContextEngineID(), // local engine ID
                new OctetString(this.community), // default context name
                new OctetString(), // transport tag
                new Integer32(StorageType.nonVolatile), // storage type
                new Integer32(RowStatus.active) // row status
        };
        MOTableRow row = communityMIB.getSnmpCommunityEntry().createRow(
                new OctetString(this.community+"2"+this.community).toSubIndex(true), com2sec);
        communityMIB.getSnmpCommunityEntry().addRow((SnmpCommunityMIB.SnmpCommunityEntryRow) row);
    }

    //not mine
    public void start() throws IOException
    {
        init();
        addShutdownHook();
        getServer().addContext(new OctetString("unpredictable"));
        finishInit();
        run();
        sendColdStartNotification();
    }

    @Override
    public String toString() {
        return "Agent{" +
                "community='" + community + '\'' +
                ", frequency=" + frequency +
                ", table_entries=" + table_entries +
                ", hexa_size=" + hexa_size +
                ", seed_path='" + seed_path + '\'' +
                ", reset_password='" + reset_password + '\'' +
                ", address='" + address + '\'' +
                ", matrix=" + matrix +
                '}';
    }

    public void unregisterManagedObject(MOGroup moGroup) {
        moGroup.unregisterMOs(server, getContext(moGroup));
    }

    //???? isto funca =D? ???? ?? ?
    public void unregisterManagedObjects(SNMPv2MIB snmpv2MIB) {
        server.unregister((ManagedObject) snmpv2MIB,null);
    }


}
