package src.numerosaleatorios;

import org.snmp4j.agent.BaseAgent;
import org.snmp4j.agent.CommandProcessor;
import org.snmp4j.agent.mo.MOTableRow;
import org.snmp4j.agent.mo.snmp.*;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.USM;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author pedro & cruz
 */
public class Agent extends BaseAgent
{
    private int UDP_port;
    private String community;
    private float frequency;
    private int table_entries;
    private int hexa_size;
    private String seed_path;
    private String reset_password;
    private String address; // não sei para que isto serve...

    public Agent(String address, int port, String com, float freq, int entries, int hex, String path) throws IOException
    {
        super(new File("conf.agent"), new File("bootCounter.agent"), new CommandProcessor(new OctetString(MPv3.createLocalEngineID())));
        this.address = address;
        this.UDP_port      = port;
        this.community     = com;
        this.frequency     = freq;
        this.table_entries = entries;
        this.hexa_size     = hex;
        this.seed_path     = path;
    }

    public String getReset_password()
    {
        return this.reset_password;
    }

    public void setReset_password(String pwd)
    {
        this.reset_password = pwd;
    }

    public static Agent initialize_agent(String filename) throws IOException
    {
        FileReader params = null;
        String line;
        ArrayList<String> args = null;
        BufferedReader br;
        try
        {
            params = new FileReader(filename);
            br = new BufferedReader(params);
            args = new ArrayList<>();
            while((line = br.readLine()) != null)
            {
                args.add(line.split(" ")[1]);
            }
        }
        catch (FileNotFoundException e)
        {
                System.out.println("File not found.");
        }
        finally
        {
            params.close();
        }
        Agent a = new Agent("", Integer.parseInt(args.get(0)), args.get(1), Float.valueOf(args.get(2)), Integer.parseInt(args.get(3)), Integer.parseInt(args.get(4)), args.get(5));
        return a;
    }



    public static void main(String[] args) throws IOException
    {
        Agent x = initialize_agent("conf.txt");
        x.setReset_password(args[1]);

    }

    @Override
    protected void registerManagedObjects()
    {
        //registerManagedObject(MOScalarFactory.createReadOnly(sysDescr,"MySystemDescr"));
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
