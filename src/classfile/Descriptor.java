package classfile;

/** A descriptor is a string representing the type of a field or method. Descriptors are
represented in the class file format using modified UTF-8 strings (§4.5.7) and thus
may be drawn, where not further constrained, from the entire Unicode character set.

A signature is a string representing the generic type of a field or method, or
generic type information for a class declaration.
 */
public class Descriptor {

    /** A field descriptor represents the type of a class, instance, or local variable. It is a
    series of characters generated by the grammar:
    FieldDescriptor:
    FieldType
    ComponentType:
    FieldType
    FieldType:
    BaseType
    ObjectType
    ArrayType
    BaseType:
    B
    C
    D
    F
    I
    J
    S
    Z
    ObjectType:
    L Classname;
    ArrayType:
    [ComponentType
    The characters of BaseType, the L and ; of ObjectType, and the [ of ArrayType are all
    ASCII characters. The Classname represents a fully qualified class or interface name. For
    historical reasons it is encoded in internal form (§4.2). A type descriptor reprenting an
    array type is valid only if it represents a type with 255 or fewer dimensions.

    The interpretation of the field types is as shown in Table 4.2.
    BaseType Character Type Interpretation
    B byte signed byte
    C char Unicode character
    D double double-precision floating-point value
    F float single-precision floating-point value
    I int integer
    J long long integer
    L Classname; reference an instance of class <classname>
    S short signed short
    Z boolean true or false
    [ reference one array dimension

    For example, the descriptor of an instance variable of type int is simply I. The descriptor
    of an instance variable of type Object is Ljava/lang/Object;. Note that the internal
    form of the fully qualified name for class Object is used. The descriptor of an instance
    variable that is a multidimensional double array,
    double d[][][];
    is
    [[[D
     */
    public static final String toFieldDescriptor(FieldInfo f) {
        return null;
    }

    public static final String getFullyQualifiedName(char[] internalForm) {
        StringBuilder buf = new StringBuilder();
        char[] cn2 = internalForm.clone();
        for (int i = 0, len = internalForm.length; i < len; ++i) {
            char c = internalForm[i];
            switch (c) {
                case 'B':
                    buf.append("byte");
                    break;
                case 'C':
                    buf.append("char");
                    break;
                case 'D':
                    buf.append("double");
                    break;
                case 'F':
                    buf.append("float");
                    break;
                case 'I':
                    buf.append("int");
                    break;
                case 'J':
                    buf.append("long");
                    break;
                case 'L':
                    for (int j = i + 1; j < len; ++j) {
                        c = internalForm[j];
                        if (c == ';') {
                            buf.append(internalForm, i, j - i - 1);
                            i = j + 1;
                            break;
                        }
                        if (c == '/') {
                            buf.append(internalForm, i, j - i-1);
                            buf.append('.');
                            i = j + 1;
                        }
                    }
                    break;
                case 'S':
                    buf.append("short");
                    break;
                case 'Z':
                    buf.append("boolean");
                    break;
                case '[':
                    for (int j = i + 1; j < len; ++j) {
                        c = internalForm[j];
                        if (c == '[') {
                            buf.append(internalForm, i, j - i - 1);
                            i = j + 1;
                            break;
                        }
                    }
                    break;
            }
        }
        return buf.toString();
    }

    /** A method descriptor represents the parameters that the method takes and the value
    that it returns:
    MethodDescriptor:
    ( ParameterDescriptor* ) ReturnDescriptor
    A parameter descriptor represents a parameter passed to a method:
    ParameterDescriptor:
    FieldType
    A return descriptor represents the type of the value returned from a method. It is a series
    of characters generated by the grammar:
    ReturnDescriptor:
    FieldType
    VoidDescriptor
    VoidDescriptor:
    V
    The character V indicates that the method returns no value (its return type is void).
    A method descriptor is valid only if it represents method parameters with a total length of
    255 or less, where that length includes the contribution for this in the case of instance or
    interface method invocations. The total length is calculated by summing the contributions
    of the individual parameters, where a parameter of type long or double contributes two
    units to the length and a parameter of any other type contributes one unit.
    For example, the method descriptor for the method
    Object mymethod(int i, double d, Thread t)
    is
    (IDLjava/lang/Thread;)Ljava/lang/Object;
    Note that internal forms of the fully qualified names of Thread and Object are
    used in the method descriptor.
    The method descriptor for mymethod is the same whether mymethod is a class or an
    instance method. Although an instance method is passed this, a reference to the current
    class instance, in addition to its intended parameters, that fact is not reflected in the
    method descriptor. (A reference to this is not passed to a class method.) The reference
    to this is passed implicitly by the method invocation instructions of the Java virtual
    machine used to invoke instance methods. */
    public String toMethodDescriptor(MethodInfo m) {
        return null;
    }
}
