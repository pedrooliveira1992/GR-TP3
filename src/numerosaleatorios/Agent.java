package src.numerosaleatorios;

import org.snmp4j.agent.BaseAgent;
import org.snmp4j.agent.CommandProcessor;
import org.snmp4j.agent.DuplicateRegistrationException;
import org.snmp4j.agent.ManagedObject;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.agent.mo.MOTableRow;
import org.snmp4j.agent.mo.snmp.*;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.USM;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author pedro & cruz
 */
public class Agent extends BaseAgent
{
    private String community;
    private float frequency;
    private int table_entries;
    private int hexa_size;
    private String seed_path;
    private String reset_password;
    private String address;

    final OID oidUnpredictableRefresh =
            new OID(new int[] { 1,3,6,1,3,4,1,1,1,0 });
    final OID oidUnpredictableEntradas =
            new OID(new int[] { 1,3,6,1,3,4,1,1,2,0 });
    final OID oidUnpredictableDigitos =
            new OID(new int[] { 1,3,6,1,3,4,1,1,3,0 });
    final OID oidUnpredictableAuthorization =
            new OID(new int[] { 1,3,6,1,3,4,1,1,4,0 });


    public Agent(String address, String com, float freq, int entries, int hex, String path, String pwd) throws IOException
    {
        super(new File("conf.agent"), new File("bootCounter.agent"), new CommandProcessor(new OctetString(MPv3.createLocalEngineID())));
        this.address        = address;
        this.community      = com;
        this.frequency      = freq;
        this.table_entries  = entries;
        this.hexa_size      = hex;
        this.seed_path      = path;
        this.reset_password = pwd;

        /*unpredictableParam(1) – grupo com objetos escalares que representem os parâmetros
        de funcionamento; além dos parâmetros de inicialização R, N, e D (apenas com permissões de
            leitura) deve incluir-se um objeto escalar especial (do tipo string e apenas com permissões de
        escrita) que sirvará para verificar autorizações para a operação reset do agente*/

        MOScalar refresh = MOScalarMaker.CR_RO(new OID("1,3,6,1,3,4,1,1,1,0"), this.frequency);
        MOScalar linhas  = MOScalarMaker.CR_RO(new OID("1,3,6,1,3,4,1,1,2,0"), this.table_entries);
        MOScalar n_elems = MOScalarMaker.CR_RO(new OID("1,3,6,1,3,4,1,1,3,0"), this.hexa_size);
        MOScalar reset   = MOScalarMaker.CR_WO(new OID("1,3,6,1,3,4,1,1,4,0"), this.reset_password);

        try
        {
            this.registerManagedObjects(refresh);
            this.registerManagedObjects(linhas);
            this.registerManagedObjects(n_elems);
            this.registerManagedObjects(reset);
        }
        catch (DuplicateRegistrationException e)
        {
            e.printStackTrace();
        }


    }

    public String getReset_password()
    {
        return this.reset_password;
    }

    public void setReset_password(String pwd)
    {
        this.reset_password = pwd;
    }



    protected void registerManagedObjects(ManagedObject m) throws DuplicateRegistrationException {
        server.register(m, new OctetString("public"));
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

    @Override
    protected void addViews(VacmMIB vacmMIB)
    {

    }

    @Override
    protected void addCommunities(SnmpCommunityMIB snmpCommunityMIB)
    {
        Variable[] vars = new Variable[]
                {
                        new OctetString("public"),
                        getAgent().getContextEngineID(),
                        new OctetString("public"),
                        new OctetString(),
                        new Integer32(StorageType.nonVolatile),
                        new Integer32(RowStatus.active)
                };
        MOTableRow row = snmpCommunityMIB.getSnmpCommunityEntry().createRow(new OctetString("public2public").toSubIndex(true), vars);
        snmpCommunityMIB.getSnmpCommunityEntry().addRow((SnmpCommunityMIB.SnmpCommunityEntryRow) row);
    }
}
