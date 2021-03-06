package classfile;

import classfile.constant.*;

/**
4.2 The ClassFile Structure
A class file consists of a single ClassFile structure:
 */
public class ClassFile {

    /** The magic item supplies the magic number identifying the class
    file format; it has the value 0xCAFEBABE. */
    public int magic = 0xcafebabe;
    /** The values of the minor_version and major_version items are
    the minor and major version numbers of this class file.Together,
    a major and a minor version number determine the version of the
    class file format. If a class file has major version number M
    and minor version number m, we denote the version of its class
    file format as M.m. Thus, class file format versions may be
    ordered lexicographically, for example, 1.5 &lt; 2.0 &lt; 2.1.
    A Java virtual machine implementation can support a class
    file format of version v if and only if v lies in some contiguous
    range Mi.0 ≤ v ≤ Mj.m. Only Sun can specify what range of
    versions a Java virtual machine implementation conforming to a
    certain release level of the Java platform may support.1
    1 The Java virtual machine implementation of Sun’s JDK release 1.0.2 supports
    class file format versions 45.0 through 45.3 inclusive. Sun’s JDK releases
    1.1.X can support class file formats of versions in the range 45.0 through
    45.65535 inclusive. For implementations of version 1.k of the Java 2
    platform can support class file formats of versions in the range 45.0 through
    44+k.0 inclusive. */
    public short minorVersion = 0x0003;
    public short majorVersion = 0x002d;
    /** The value of the constant_pool_count item is equal to the
    number of entries in the constant_pool table plus one. A
    constant_pool index is considered valid if it is greater than zero
    and less than constant_pool_count, with the exception for
    constants of type long and double noted in §4.5.5. */
    public short constantPoolCount;
    /** The constant_pool is a table of structures (§4.5) representing
    various string constants, class and interface names, field names,
    and other constants that are referred to within the ClassFile
    structure and its substructures. The format of each constant_pool
    table entry is indicated by its first “tag” byte.
    The constant_pool table is indexed from 1 to
    constant_pool_count-1. */
    public CpInfo[] constantPool;
    /** The value of the access_flags item is a mask of flags used to
    denote access permissions to and properties of this class or
    interface. The interpretation of each flag, when set, is as shown in
    Table 4.1.

    Table 4.1 Class access and property modifiers
    Flag Name       Value   Interpretation
    ACC_PUBLIC      0x0001  Declared public; may be accessed from outside its package.
    ACC_FINAL       0x0010  Declared final; no subclasses allowed.
    ACC_SUPER       0x0020  Treat superclass methods specially when invoked by the invokespecial instruction.
    ACC_INTERFACE   0x0200  Is an interface, not a class.
    ACC_ABSTRACT    0x0400  Declared abstract; must not be instantiated.
    ACC_SYNTHETIC   0x1000  Declared synthetic; Not present in the source code.
    ACC_ANNOTATION  0x2000  Declared as an annotation type.
    ACC_ENUM        0x4000  Declared as an enum type.

    A class may be marked with the ACC_SYNTHETIC flag to
    indicate that it was generated by the compiler and does not appear
    in the source code.
    The ACC_ENUM bit indicates that this class or its
    superclass is declared as an enumerated type.
    An interface is distinguished by its ACC_INTERFACE flag
    being set. If its ACC_INTERFACE flag is not set, this class file
    defines a class, not an interface.
    If the ACC_INTERFACE flag of this class file is set, its
    ACC_ABSTRACT flag must also be set (JLS §9.1.1.1). Such a class
    file must not have its ACC_FINAL, ACC_SUPER or ACC_ENUM flags set.
    An annotation type must have its ACC_ANNOTATION flag
    set. If the ACC_ANNOTATION flag is set, the
    ACC_INTERFACE flag must be set as well.
    If the ACC_INTERFACE flag of this class file is not set, it may
    have any of the other flags in Table 4.1 set, except the ACC_ANNOTATION flag. However, such a class file cannot
    have both its ACC_FINAL and ACC_ABSTRACT flags set (§2.8.2).
    The setting of the ACC_SUPER flag indicates which of two
    alternative semantics for its invokespecial instruction the Java
    virtual machine is to express; the ACC_SUPER flag exists for
    backward compatibility for code compiled by Sun’s older
    compilers for the Java programming language. All new
    implementations of the Java virtual machine should implement
    the semantics for invokespecial documented in this specification.
    All new compilers to the instruction set of the Java virtual
    machine should set the ACC_SUPER flag.

		The ACC_SUPER flag exists for backward compatibility with code compiled by
		older compilers for the Java programming language. In Oracle’s JDK prior to
		release 1.0.2, the compiler generated ClassFile access_flags in which the
		flag now representing ACC_SUPER had no assigned meaning, and Oracle’s
		Java virtual machine implementation ignored the flag if it was set.

    All bits of the access_flags item not assigned in Table 4.1
    are reserved for future use. They should be set to zero in
    generated class files and should be ignored by Java virtual
    machine implementations. */
    public short accessFlags;
    /** The value of the this_class item must be a valid index into the
    constant_pool table. The constant_pool entry at that index
    must be a CONSTANT_Class_info (§4.5.1) structure representing
    the class or interface defined by this class file. */
    public short thisClass;
    /** For a class, the value of the super_class item either must be
    zero or must be a valid index into the constant_pool table. If the
    value of the super_class item is nonzero, the constant_pool
    entry at that index must be a CONSTANT_Class_info (§4.5.1)
    structure representing the direct superclass of the class defined by
    this class file. Neither the direct superclass nor any of its
    superclasses may be a final class.
    If the value of the super_class item is zero, then this class
    file must represent the class Object, the only class or interface
    without a direct superclass.
    For an interface, the value of the super_class item must
    always be a valid index into the constant_pool table. The
    constant_pool entry at that index must be a
    CONSTANT_Class_info structure representing the class Object. */
    public short superClass;
    /** The value of the interfaces_count item gives the number of
    direct superinterfaces of this class or interface type. */
    public short interfacesCount;
    /** Each value in the interfaces array must be a valid index into
    the constant_pool table. The constant_pool entry at each
    value of interfaces[i], where 0 ≤ i &lt; interfaces_count,
    must be a CONSTANT_Class_info (§4.5.1) structure representing
    an interface that is a direct superinterface of this class or interface
    type, in the left-to-right order given in the source for the type. */
    public short[] interfaces;
    /** The value of the fields_count item gives the number of
    field_info structures in the fields table. The field_info
    (§4.6) structures represent all fields, both class variables and
    instance variables, declared by this class or interface type. */
    public short fieldsCount;
    /** Each value in the fields table must be a field_info (§4.6)
    structure giving a complete description of a field in this class or
    interface. The fields table includes only those fields that are
    declared by this class or interface. It does not include items
    representing fieldsthat are inherited from superclasses or
    superinterfaces. */
    public FieldInfo[] fields;
    /** The value of the methods_count item gives the number of
    method_info structures in the fields table. */
    public short methodsCount;
    /** Each value in the methods table must be a method_info (§4.6)
		structure giving a complete description of a method in this class
		or interface. If neither of the ACC_NATIVE and ACC_ABSTRACT
		flags are set in the access_flags item of a method_info
		structure, the Java virtual machine instructions implementing the
		method are also supplied.
    The method_info structures represent all fields declared
    by this class or interface type, including instance fields, class
    (static) fields, instance initialization fields (§3.9), and
    any class or interface initialization method (§3.9). The fields
    table does not include items representing fields that are
    inherited from superclasses or superinterfaces. */
    public MethodInfo[] methods;
    /** The value of the attributes_count item gives the number of
    attributes (§4.8) in the attributes table of this class. */
    public short attributesCount;
    /** Each value of the attributes table must be an attribute_info
		structure (§4.7).
		The attributes defined by this specification as appearing in the
		attributes table of a ClassFile structure are the InnerClasses
		(§4.7.6), EnclosingMethod (§4.7.7), Synthetic (§4.7.8),
		Signature (§4.7.9), SourceFile (§4.7.10),
		SourceDebugExtension (§4.7.11), Deprecated (§4.7.15),
		RuntimeVisibleAnnotations (§4.7.16),
		RuntimeInvisibleAnnotations (§4.7.17), and
		BootstrapMethods (§4.7.21) attributes.
		If a Java virtual machine implementation recognizes class files
		whose version number is 49.0 or above, it must recognize and
		correctly read Signature (§4.7.9), RuntimeVisibleAnnotations
		(§4.7.16), and RuntimeInvisibleAnnotations (§4.7.17) attributes
		found in the attributes table of a ClassFile structure of a class
		file whose version number is 49.0 or above.
		If a Java virtual machine implementation recognizes class files
		whose version number is 51.0 or above, it must recognize and
		correctly read BootstrapMethods (§4.7.21) attributes found in the
		attributes table of a ClassFile structure of a class file whose
		version number is 51.0 or above.
		A Java virtual machine implementation is required to silently
		ignore any or all attributes in the attributes table of a ClassFile
		structure that it does not recognize. Attributes not defined in this
		specification are not allowed to affect the semantics of the class file,
		but only to provide additional descriptive information (§4.7.1). */
    public AttributeInfo[] attributes;

    public boolean isValid() {
        if (constantPool.length != constantPoolCount - 1) {
            return false;
        }
        if (interfaces.length != interfacesCount) {
            return false;
        }
        if (fields.length != fieldsCount) {
            return false;
        }
        if (methods.length != methodsCount) {
            return false;
        }
        if (attributes.length != attributesCount) {
            return false;
        }

        return true;
    }

    public CpInfo getCpInfo(short index) {
        return constantPool[(0xffff & index) - 1];
    }
    public static final int ERR_ACC_INTERFACE_NOT_ABSTRACT = 1;
    public static final int ERR_ACC_INTERFACE_INVALID_FLAGS = 2;

    public static final int validateAccessFlags(short accessFlags) {
        if ((accessFlags & ACC_INTERFACE) != 0) {
            if ((accessFlags & ACC_ABSTRACT) == 0) {
                return ERR_ACC_INTERFACE_NOT_ABSTRACT;
            }
            if ((accessFlags & (ACC_FINAL | ACC_SUPER | ACC_ENUM)) != 0) {
                return ERR_ACC_INTERFACE_INVALID_FLAGS;
            }
        }
        if ((accessFlags & ACC_ANNOTATION) != 0) {
        }

        return 0;
    }
    /** Declared public; may be accessed from outside its package. */
    public final static short ACC_PUBLIC = (short) 0x0001;
    /** Declared final; no subclasses allowed. */
    public final static short ACC_FINAL = (short) 0x0010;
    /** Treat superclass fields specially when invoked by the invokespecial instruction. */
    public final static short ACC_SUPER = (short) 0x0020;
    /** Is an interface, not a class. */
    public final static short ACC_INTERFACE = (short) 0x0200;
    /** Declared abstract; may not be instantiated. */
    public final static short ACC_ABSTRACT = (short) 0x0400;
    /** Declared synthetic; Not present in the source code. */
    public final static short ACC_SYNTHETIC = (short) 0x1000;
    /** Declared as an annotation type. */
    public final static short ACC_ANNOTATION = (short) 0x2000;
    /** Declared as an enum type. */
    public final static short ACC_ENUM = (short) 0x4000;
}
