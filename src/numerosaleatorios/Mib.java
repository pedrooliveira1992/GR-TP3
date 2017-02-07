package numerosaleatorios;

import org.snmp4j.agent.*;
import org.snmp4j.agent.mo.*;
import org.snmp4j.agent.mo.snmp.DisplayStringScalar;
import org.snmp4j.agent.request.SubRequest;
import org.snmp4j.log.LogAdapter;
import org.snmp4j.log.LogFactory;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;

public class Mib implements MOGroup{
    private static final LogAdapter LOGGER = LogFactory.getLogger(Mib.class);

//--AgentGen BEGIN=_STATIC
//--AgentGen END

  // Factory
  private MOFactory moFactory = DefaultMOFactory.getInstance();

  // Constants 

  /**
   * OID of this MIB module for usage which can be 
   * used for its identification.
   */
  public static final OID oidUminhoGrMib =
    new OID(new int[] { 1,3,6,1,3,4 });

  // Identities
  // Scalars
  public static final OID oidUnpredictableRefresh = 
    new OID(new int[] { 1,3,6,1,3,4,1,1,1,0 });
  public static final OID oidUnpredictableEntradas = 
    new OID(new int[] { 1,3,6,1,3,4,1,1,2,0 });
  public static final OID oidUnpredictableDigitos = 
    new OID(new int[] { 1,3,6,1,3,4,1,1,3,0 });
  public static final OID oidUnpredictableAuthorization = 
    new OID(new int[] { 1,3,6,1,3,4,1,1,4,0 });
  // Tables

  // Notifications
  public static final OID oidUminhoMIB0 =
    new OID(new int[] { 1,3,6,1,3,4,2,1 });   
  public static final OID oidTrapVarUnpredictableRefresh =
    new OID(new int[] { 1,3,6,1,3,4,1,1,1 });
  public static final OID oidTrapVarUnpredictableEntradas =
    new OID(new int[] { 1,3,6,1,3,4,1,1,2 });
  public static final OID oidTrapVarUnpredictableDigitos =
    new OID(new int[] { 1,3,6,1,3,4,1,1,3 });
  public static final OID oidTrapVarUnpredictableAuthorization =
    new OID(new int[] { 1,3,6,1,3,4,1,1,4 });
  public static final OID oidTrapVarUnpredIndex =
    new OID(new int[] { 1,3,6,1,3,4,1,2,1,1,1 });
  public static final OID oidTrapVarUnpredNumber =
    new OID(new int[] { 1,3,6,1,3,4,1,2,1,1,2 });


  // Enumerations




  // TextualConventions
  private static final String TC_MODULE_SNMPV2_TC = "SNMPv2-TC";
  private static final String TC_DISPLAYSTRING = "DisplayString";

  // Scalars
  private MOScalar<Integer32> unpredictableRefresh;
  private MOScalar<Integer32> unpredictableEntradas;
  private MOScalar<Integer32> unpredictableDigitos;
  private MOScalar<OctetString> unpredictableAuthorization;

  // Tables
  public static final OID oidUnpredEntry = 
    new OID(new int[] { 1,3,6,1,3,4,1,2,1,1 });

  // Index OID definitions
  public static final OID oidUnpredIndex =
    new OID(new int[] { 1,3,6,1,3,4,1,2,1,1,1 });

  // Column TC definitions for unpredEntry:
  public static final String tcModuleSNMPv2Tc = "SNMPv2-TC";
  public static final String tcDefDisplayString = "DisplayString";
    
  // Column sub-identifier definitions for unpredEntry:
  public static final int colUnpredIndex = 1;
  public static final int colUnpredNumber = 2;

  // Column index definitions for unpredEntry:
  public static final int idxUnpredIndex = 0;
  public static final int idxUnpredNumber = 1;

  private MOTableSubIndex[] unpredEntryIndexes;
  private MOTableIndex unpredEntryIndex;
  
  private MOTable<UnpredEntryRow,
                  MOColumn,
                  MOTableModel<UnpredEntryRow>> unpredEntry;
  private MOTableModel<UnpredEntryRow> unpredEntryModel;


//--AgentGen BEGIN=_MEMBERS
//--AgentGen END

  /**
   * Constructs a UMINHO-GR-MIB instance without actually creating its
   * <code>ManagedObject</code> instances. This has to be done in a
   * sub-class constructor or after construction by calling 
   * {@link #createMO(MOFactory moFactory)}. 
   */
  protected Mib() {
//--AgentGen BEGIN=_DEFAULTCONSTRUCTOR
//--AgentGen END
  }

  /**
   * Constructs a UMINHO-GR-MIB instance and actually creates its
   * <code>ManagedObject</code> instances using the supplied 
   * <code>MOFactory</code> (by calling
   * {@link #createMO(MOFactory moFactory)}).
   * @param moFactory
   *    the <code>MOFactory</code> to be used to create the
   *    managed objects for this module.
   */
  public Mib(MOFactory moFactory) {
    this();
    createMO(moFactory);
//--AgentGen BEGIN=_FACTORYCONSTRUCTOR
//--AgentGen END
  }

//--AgentGen BEGIN=_CONSTRUCTORS
//--AgentGen END

  /**
   * Create the ManagedObjects defined for this MIB module
   * using the specified {@link MOFactory}.
   * @param moFactory
   *    the <code>MOFactory</code> instance to use for object 
   *    creation.
   */
  protected void createMO(MOFactory moFactory) {
    addTCsToFactory(moFactory);
    unpredictableRefresh = 
      moFactory.createScalar(oidUnpredictableRefresh,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY), 
                             new Integer32());
    unpredictableEntradas = 
      moFactory.createScalar(oidUnpredictableEntradas,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY), 
                             new Integer32());
    unpredictableDigitos = 
      moFactory.createScalar(oidUnpredictableDigitos,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY), 
                             new Integer32());
    unpredictableAuthorization = 
      new UnpredictableAuthorization(oidUnpredictableAuthorization, 
                                     moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE));
    unpredictableAuthorization.addMOValueValidationListener(new UnpredictableAuthorizationValidator());
    createUnpredEntry(moFactory);
  }

  public MOScalar<Integer32> getUnpredictableRefresh() {
    return unpredictableRefresh;
  }
  public MOScalar<Integer32> getUnpredictableEntradas() {
    return unpredictableEntradas;
  }
  public MOScalar<Integer32> getUnpredictableDigitos() {
    return unpredictableDigitos;
  }
  public MOScalar<OctetString> getUnpredictableAuthorization() {
    return unpredictableAuthorization;
  }

  public MOTable<UnpredEntryRow,MOColumn,MOTableModel<UnpredEntryRow>> getUnpredEntry() {
    return unpredEntry;
  }


  @SuppressWarnings(value={"unchecked"})
  private void createUnpredEntry(MOFactory moFactory) {
    // Index definition
    unpredEntryIndexes = 
      new MOTableSubIndex[] {
      moFactory.createSubIndex(oidUnpredIndex, 
                               SMIConstants.SYNTAX_OCTET_STRING, 0, 255)
    };

    unpredEntryIndex = 
      moFactory.createIndex(unpredEntryIndexes,
                            false,
                            new MOTableIndexValidator() {
      public boolean isValidIndex(OID index) {
        boolean isValidIndex = true;
     //--AgentGen BEGIN=unpredEntry::isValidIndex
     //--AgentGen END
        return isValidIndex;
      }
    });

    // Columns
    MOColumn[] unpredEntryColumns = new MOColumn[2];
    unpredEntryColumns[idxUnpredIndex] = 
      moFactory.createColumn(colUnpredIndex, 
                             SMIConstants.SYNTAX_OCTET_STRING,
                             moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_ONLY),
                             tcModuleSNMPv2Tc,
                             tcDefDisplayString);
    unpredEntryColumns[idxUnpredNumber] = 
      new MOMutableColumn<Integer32>(colUnpredNumber,
                          SMIConstants.SYNTAX_INTEGER,
                          moFactory.createAccess(MOAccessImpl.ACCESSIBLE_FOR_READ_WRITE),
                          (Integer32)null);
    ((MOMutableColumn)unpredEntryColumns[idxUnpredNumber]).
      addMOValueValidationListener(new UnpredNumberValidator());
    // Table model
    unpredEntryModel = 
      moFactory.createTableModel(oidUnpredEntry,
                                 unpredEntryIndex,
                                 unpredEntryColumns);
    ((MOMutableTableModel<UnpredEntryRow>)unpredEntryModel).setRowFactory(
      new UnpredEntryRowFactory());
    unpredEntry = 
      moFactory.createTable(oidUnpredEntry,
                            unpredEntryIndex,
                            unpredEntryColumns,
                            unpredEntryModel);
  }



  public void registerMOs(MOServer server, OctetString context) 
    throws DuplicateRegistrationException 
  {
    // Scalar Objects
    server.register(this.unpredictableRefresh, context);
    server.register(this.unpredictableEntradas, context);
    server.register(this.unpredictableDigitos, context);
    server.register(this.unpredictableAuthorization, context);
    server.register(this.unpredEntry, context);
//--AgentGen BEGIN=_registerMOs
//--AgentGen END
  }

  public void unregisterMOs(MOServer server, OctetString context) {
    // Scalar Objects
    server.unregister(this.unpredictableRefresh, context);
    server.unregister(this.unpredictableEntradas, context);
    server.unregister(this.unpredictableDigitos, context);
    server.unregister(this.unpredictableAuthorization, context);
    server.unregister(this.unpredEntry, context);
//--AgentGen BEGIN=_unregisterMOs
//--AgentGen END
  }

  // Notifications
  public void uminhoMIB0(NotificationOriginator notificationOriginator,
                         OctetString context, VariableBinding[] vbs) {
    if (vbs.length < 6) {
      throw new IllegalArgumentException("Too few notification objects (uminhoMIB0): "+
                                         vbs.length+"<6");
    }
    // unpredictableRefresh
    if (!(vbs[0].getOid().startsWith(oidTrapVarUnpredictableRefresh))) {
      throw new IllegalArgumentException("Variable 0 (unpredictableRefresh)) has wrong OID: "+vbs[0].getOid()+
                                         " does not start with "+oidTrapVarUnpredictableRefresh);
    }

    // unpredictableEntradas
    if (!(vbs[1].getOid().startsWith(oidTrapVarUnpredictableEntradas))) {
      throw new IllegalArgumentException("Variable 1 (unpredictableEntradas)) has wrong OID: "+vbs[1].getOid()+
                                         " does not start with "+oidTrapVarUnpredictableEntradas);
    }

    // unpredictableDigitos
    if (!(vbs[2].getOid().startsWith(oidTrapVarUnpredictableDigitos))) {
      throw new IllegalArgumentException("Variable 2 (unpredictableDigitos)) has wrong OID: "+vbs[2].getOid()+
                                         " does not start with "+oidTrapVarUnpredictableDigitos);
    }

    // unpredictableAuthorization
    if (!(vbs[3].getOid().startsWith(oidTrapVarUnpredictableAuthorization))) {
      throw new IllegalArgumentException("Variable 3 (unpredictableAuthorization)) has wrong OID: "+vbs[3].getOid()+
                                         " does not start with "+oidTrapVarUnpredictableAuthorization);
    }
	{
		OctetString os = (OctetString)vbs[3].getVariable();
		if (!(((os.length() >= 0) && (os.length() <= 255)))) {
			throw new IllegalArgumentException("Illegal length of variable 3 (unpredictableAuthorization)): " + os.length());
		}
    }

    // unpredIndex
    if (!(vbs[4].getOid().startsWith(oidTrapVarUnpredIndex))) {
      throw new IllegalArgumentException("Variable 4 (unpredIndex)) has wrong OID: "+vbs[4].getOid()+
                                         " does not start with "+oidTrapVarUnpredIndex);
    }
    if (!unpredEntryIndex.isValidIndex(unpredEntry.getIndexPart(vbs[4].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 4 (unpredIndex)) specified: "+
                                         unpredEntry.getIndexPart(vbs[4].getOid()));
    }
	{
		OctetString os = (OctetString)vbs[4].getVariable();
		if (!(((os.length() >= 0) && (os.length() <= 255)))) {
			throw new IllegalArgumentException("Illegal length of variable 4 (unpredIndex)): " + os.length());
		}
    }

    // unpredNumber
    if (!(vbs[5].getOid().startsWith(oidTrapVarUnpredNumber))) {
      throw new IllegalArgumentException("Variable 5 (unpredNumber)) has wrong OID: "+vbs[5].getOid()+
                                         " does not start with "+oidTrapVarUnpredNumber);
    }
    if (!unpredEntryIndex.isValidIndex(unpredEntry.getIndexPart(vbs[5].getOid()))) {
      throw new IllegalArgumentException("Illegal index for variable 5 (unpredNumber)) specified: "+
                                         unpredEntry.getIndexPart(vbs[5].getOid()));
    }

    notificationOriginator.notify(context, oidUminhoMIB0, vbs);
  }
  
  


  // Scalars
  public class UnpredictableAuthorization extends DisplayStringScalar<OctetString> {
    UnpredictableAuthorization(OID oid, MOAccess access) {
      super(oid, access, new OctetString(),
            0, 
            255);
//--AgentGen BEGIN=unpredictableAuthorization
//--AgentGen END
    }

    public int isValueOK(SubRequest request) {
      Variable newValue =
        request.getVariableBinding().getVariable();
      int valueOK = super.isValueOK(request);
      if (valueOK != SnmpConstants.SNMP_ERROR_SUCCESS) {
      	return valueOK;
      }
      OctetString os = (OctetString)newValue;
      if (!(((os.length() >= 0) && (os.length() <= 255)))) {
        valueOK = SnmpConstants.SNMP_ERROR_WRONG_LENGTH;
      }
     //--AgentGen BEGIN=unpredictableAuthorization::isValueOK
     //--AgentGen END
      return valueOK; 
    }

    public OctetString getValue() {
     //--AgentGen BEGIN=unpredictableAuthorization::getValue
     //--AgentGen END
      return super.getValue();    
    }

    public int setValue(OctetString newValue) {
     //--AgentGen BEGIN=unpredictableAuthorization::setValue
     //--AgentGen END
      return super.setValue(newValue);    
    }

     //--AgentGen BEGIN=unpredictableAuthorization::_METHODS
     //--AgentGen END

  }


  // Value Validators
  /**
   * The <code>UnpredictableAuthorizationValidator</code> implements the value
   * validation for <code>UnpredictableAuthorization</code>.
   */
  static class UnpredictableAuthorizationValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
      OctetString os = (OctetString)newValue;
      if (!(((os.length() >= 0) && (os.length() <= 255)))) {
        validationEvent.setValidationStatus(SnmpConstants.SNMP_ERROR_WRONG_LENGTH);
        return;
      }
     //--AgentGen BEGIN=unpredictableAuthorization::validate
     //--AgentGen END
    }
  }

  /**
   * The <code>UnpredNumberValidator</code> implements the value
   * validation for <code>UnpredNumber</code>.
   */
  static class UnpredNumberValidator implements MOValueValidationListener {
    
    public void validate(MOValueValidationEvent validationEvent) {
      Variable newValue = validationEvent.getNewValue();
     //--AgentGen BEGIN=unpredNumber::validate
     //--AgentGen END
    }
  }

  // Rows and Factories

  public class UnpredEntryRow extends DefaultMOMutableRow2PC {

     //--AgentGen BEGIN=unpredEntry::RowMembers
     //--AgentGen END

    public UnpredEntryRow(OID index, Variable[] values) {
      super(index, values);
     //--AgentGen BEGIN=unpredEntry::RowConstructor
     //--AgentGen END
    }
    
    public OctetString getUnpredIndex() {
     //--AgentGen BEGIN=unpredEntry::getUnpredIndex
     //--AgentGen END
      return (OctetString) super.getValue(idxUnpredIndex);
    }  
    
    public void setUnpredIndex(OctetString newColValue) {
     //--AgentGen BEGIN=unpredEntry::setUnpredIndex
     //--AgentGen END
      super.setValue(idxUnpredIndex, newColValue);
    }
    
    public Integer32 getUnpredNumber() {
     //--AgentGen BEGIN=unpredEntry::getUnpredNumber
     //--AgentGen END
      return (Integer32) super.getValue(idxUnpredNumber);
    }  
    
    public void setUnpredNumber(Integer32 newColValue) {
     //--AgentGen BEGIN=unpredEntry::setUnpredNumber
     //--AgentGen END
      super.setValue(idxUnpredNumber, newColValue);
    }
    
    public Variable getValue(int column) {
     //--AgentGen BEGIN=unpredEntry::RowGetValue
     //--AgentGen END
      switch(column) {
        case idxUnpredIndex: 
        	return getUnpredIndex();
        case idxUnpredNumber: 
        	return getUnpredNumber();
        default:
          return super.getValue(column);
      }
    }
    
    public void setValue(int column, Variable value) {
     //--AgentGen BEGIN=unpredEntry::RowSetValue
     //--AgentGen END
      switch(column) {
        case idxUnpredIndex: 
        	setUnpredIndex((OctetString)value);
        	break;
        case idxUnpredNumber: 
        	setUnpredNumber((Integer32)value);
        	break;
        default:
          super.setValue(column, value);
      }
    }

     //--AgentGen BEGIN=unpredEntry::Row
     //--AgentGen END
  }
  
  class UnpredEntryRowFactory 
        implements MOTableRowFactory<UnpredEntryRow>
  {
    public synchronized UnpredEntryRow createRow(OID index, Variable[] values)
        throws UnsupportedOperationException 
    {
      UnpredEntryRow row = 
        new UnpredEntryRow(index, values);
     //--AgentGen BEGIN=unpredEntry::createRow
     //--AgentGen END
      return row;
    }
    
    public synchronized void freeRow(UnpredEntryRow row) {
     //--AgentGen BEGIN=unpredEntry::freeRow
     //--AgentGen END
    }

     //--AgentGen BEGIN=unpredEntry::RowFactory
     //--AgentGen END
  }


//--AgentGen BEGIN=_METHODS
//--AgentGen END

  // Textual Definitions of MIB module UMINHO-GR-MIB
  protected void addTCsToFactory(MOFactory moFactory) {
  }


//--AgentGen BEGIN=_TC_CLASSES_IMPORTED_MODULES_BEGIN
//--AgentGen END

  // Textual Definitions of other MIB modules
  public void addImportedTCsToFactory(MOFactory moFactory) {
  }
}
