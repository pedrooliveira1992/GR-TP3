package numerosaleatorios;

import org.snmp4j.agent.DuplicateRegistrationException;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by cruz on 2/2/17.
 */
public class snmpJ
{
    public static void main(String[] args) throws DuplicateRegistrationException {
        final OID oidUminhoGrMib = new OID(new int[] { 1,3,6,1,3,4 });

        if (args.length < -1)
        {
            System.out.println("No arguments provided");
            return;
        }
        Agent agent;
        try
        {
            agent = initialize_agent("conf.txt", "asdasdasd");
            agent.start();
            agent.unregisterManagedObjects(agent.getSnmpv2MIB());
            agent.registerManagedObject(MOScalarMaker.CR_RO(oidUminhoGrMib,"MySystemDescr"));

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



    }

    public static Agent initialize_agent(String filename, String reset_pwd) throws IOException
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
        Agent a = new Agent("localhost:"+args.get(0), args.get(1), Integer.parseInt(args.get(2)), Integer.parseInt(args.get(3)), Integer.parseInt(args.get(4)), args.get(5), reset_pwd);
        return a;
    }


}
