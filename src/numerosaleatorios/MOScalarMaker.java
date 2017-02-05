package src.numerosaleatorios;

import org.snmp4j.agent.mo.MOAccessImpl;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;

/**
 * Created by cruz on 2/5/17.
 */
public class MOScalarMaker
{
    public static MOScalar CR_RO(OID oid, Object value ){
        return new MOScalar(oid, MOAccessImpl.ACCESS_READ_ONLY, getVariable(value));
    }

    public static MOScalar CR_WO(OID oid, Object value ){
        return new MOScalar(oid, MOAccessImpl.ACCESS_WRITE_ONLY, getVariable(value));
    }

    public static MOScalar CR_RW(OID oid,Object value ){
        return new MOScalar(oid,MOAccessImpl.ACCESS_READ_WRITE,getVariable(value));
    }

    //COPYPASTA
    private static Variable getVariable(Object value)
    {
        if(value instanceof String)
        {
            return new OctetString((String)value);
        }
        else if(value instanceof Integer)
        {
            return new Integer32((Integer)value);
        }
        throw new IllegalArgumentException("Unmanaged Type: " + value.getClass());
    }
}
