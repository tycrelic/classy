package classfile;
/**
4.8 Attributes
Attributes are used in the ClassFile (§4.2), field_info (§4.6), method_info
(§4.7), Code_attribute (§4.8.3) structures of the class file format. All attributes
have the following general format:
attribute_info {
u2 attribute_name_index;
u4 attribute_length;
u1 info[attribute_length];
}
For all attributes, the attribute_name_index must be a valid unsigned 16-bit
index into the constant pool of the class. The constant_pool entry at
attribute_name_index must be a CONSTANT_Utf8_info (§4.5.7) structure
representing the name of the attribute. The value of the attribute_length item
indicates the length of the subsequent information in bytes. The length does not
include the initial six bytes that contain the attribute_name_index and
attribute_length items.
Certain attributes are predefined as part of the class file specification. The
predefined attributes are the SourceFile (§4.8.10), ConstantValue (§4.8.2),
Code (§4.8.3), StackMapTable (§4.8.4), Exceptions (§4.8.5), InnerClasses
(§4.8.6), EnclosingMethod (§4.8.7), Synthetic (§4.8.8), Signature (§4.8.9),
LineNumberTable (§4.8.12), LocalVariableTable and Deprecated (§4.8.15)
attributes. Within the context of their use in this specification, that is, in the
attributes tables of the class file structures in which they appear, the names of
these predefined attributes are reserved.
Of the predefined attributes, the Code, ConstantValue and Exceptions
attributes must be recognized and correctly read by a class file reader for correct
interpretation of the class file by a Java virtual machine implementation. The
StackMapTable attribute must be recognized and correctly interpreted by any
Java virtual machine implementation that recognizes class files whose major
version is 50.0 or above. The Signature attribute must be recognized and
correctly interpreted by any Java virtual machine implementation that recognizes
class files whose major version is 49.0 or above. The InnerClasses,
EnclosingMethod and Synthetic attributes must be recognized and correctly
read by a class file reader in order to properly implement the Java and Java 2
platform class libraries (§3.12). Use of the remaining predefined attributes
is optional; a class file reader may use the information they contain, or otherwise
must silently ignore those attributes.

4.8.1 Defining and Naming New Attributes
Compilers are permitted to define and emit class files containing new attributes in
the attributes tables of class file structures. Java virtual machine implementations
are permitted to recognize and use new attributes found in the attributes
tables of class file structures. However, any attribute not defined as part of this Java
virtual machine specification must not affect the semantics of class or interface
types. Java virtual machine implementations are required to silently ignore
attributes they do not recognize.
For instance, defining a new attribute to support vendor-specific debugging is
permitted. Because Java virtual machine implementations are required to ignore
attributes they do not recognize, class files intended for that particular Java
virtual machine implementation will be usable by other implementations even if
those implementations cannot make use of the additional debugging information
that the class files contain.
Java virtual machine implementations are specifically prohibited from throwing
an exception or otherwise refusing to use class files simply because of the
presence of some new attribute. Of course, tools operating on class files may not
run correctly if given class files that do not contain all the attributes they require.
Two attributes that are intended to be distinct, but that happen to use the same
attribute name and are of the same length, will conflict on implementations that
recognize either attribute. Attributes defined other than by Sun must have names
chosen according to the package naming convention defined by The Java
Language Specification. For instance, a new attribute defined by Netscape might
have the name "com.Netscape.new-attribute".3
3 The first edition of The Java Language Specification required that "com" be in
uppercase in this example. The second edition reversed that convention and
uses lowercase.
Sun may define additional attributes in future versions of this class file
specification.
 */
public class AttributeInfo {
    public short attributeNameIndex;
    public int attributeLength;
    public byte[] info;

    public static final String SOURCE_FILE = "SourceFile";
    public static final String CONSTANT_VALUE = "ConstantValue";
    public static final String CODE = "Code";
    public static final String STACK_MAP_TABLE = "StackMapTable";
    public static final String EXCEPTIONS = "Exceptions";
    public static final String INNER_CLASSES = "InnerClasses";
    public static final String ENCLOSING_METHOD = "EnclosingMethod";
    public static final String SYNTHETIC = "Synthetic";
    public static final String SIGNATURE = "Signature";
    public static final String SOURCE_DEBUG_EXTENSION = "SourceDebugExtension";
    public static final String LINE_NUMBER_TABLE = "LineNumberTable";
    public static final String LOCAL_VARIABLE_TABLE = "LocalVariableTable";
    public static final String LOCAL_VARIABLE_TYPE_TABLE = "LocalVariableTypeTable";
    public static final String DEPRECATED = "Deprecated";

    public static final String ANNOTATION_DEFAULT = "AnnotationDefault";
    public static final String RUNTIME_VISIBLE_ANNOTATIONS = "RuntimeVisibleAnnotations";
    public static final String RUNTIME_INVISIBLE_ANNOTATIONS = "RuntimeInvisibleAnnotations";
    public static final String RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS = "RuntimeVisibleParameterAnnotations";
    public static final String RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS = "RuntimeInvisibleParameterAnnotations";
}