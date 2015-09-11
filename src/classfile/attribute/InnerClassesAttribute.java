package classfile.attribute;

/**
4.8.6 The InnerClasses Attribute
The InnerClasses attribute5 is a variable-length attribute in the attributes table
of the ClassFile (§4.2) structure. If the constant pool of a class or interface C contains
a CONSTANT_Class_info entry which represents a class or interface that is
not a member of a package, then C‘s ClassFile structure must have exactly one
InnerClasses attribute in its attributes table.
The InnerClasses attribute has the following format:
InnerClasses_attribute {
u2 attribute_name_index;
u4 attribute_length;
u2 number_of_classes;
{ u2 inner_class_info_index;
u2 outer_class_info_index;
u2 inner_name_index;
u2 inner_class_access_flags;
} classes[number_of_classes];
}
The items of the InnerClasses_attribute structure are as follows:
attribute_name_index
The value of the attribute_name_index item must be a valid
index into the constant_pool table. The constant_pool entry
at that index must be a CONSTANT_Utf8_info (§4.5.7) structure
representing the string "InnerClasses".
attribute_length
The value of the attribute_length item indicates the length of
the attribute, excluding the initial six bytes.
number_of_classes
The value of the number_of_classes item indicates the number
of entries in the classes array.
classes[]
Every CONSTANT_Class_info entry in the constant_pool table
which represents a class or interface C that is not a package
member must have exactly one corresponding entry in the
classes array.
If a class has members that are classes or interfaces, its
constant_pool table (and hence its InnerClasses attribute)
must refer to each such member, even if that member is not
otherwise mentioned by the class. These rules imply that a nested
class or interface member will have InnerClasses information
for each enclosing class and for each immediate member.
Each classes array entry contains the following four items:
inner_class_info_index
The value of the inner_class_info_index item must be
zero or a valid index into the constant_pool table. The
constant_pool entry at that index must be a
CONSTANT_Class_info (§4.5.1) structure representing C.
The remaining items in the classes array entry give
information about C.
outer_class_info_index
If C is not a member, the value of the
outer_class_info_index item must be zero. Otherwise,
the value of the outer_class_info_index item must be a
valid index into the constant_pool table, and the entry at
that index must be a CONSTANT_Class_info (§4.5.1)
structure representing the class or interface of which C is a
member.
inner_name_index
If C is anonymous, the value of the inner_name_index
item must be zero. Otherwise, the value of the
inner_name_index item must be a valid index into the
constant_pool table, and the entry at that index must be a
CONSTANT_Utf8_info (§4.5.7) structure that represents the
original simple name of C, as given in the source code from
which this class file was compiled.
inner_class_access_flags
The value of the inner_class_access_flags item is a
mask of flags used to denote access permissions to and
properties of class or interface C as declared in the source
code from which this class file was compiled. It is used by
compilers to recover the original information when source
code is not available. The flags are shown in Table 4.7.

Table 4.7 Nested class access and property flags
Flag Name Value Meaning
ACC_PUBLIC 0x0001 Marked or implicitly public in
source.
ACC_PRIVATE 0x0002 Marked private in source.
ACC_PROTECTED 0x0004 Marked protected in source.
ACC_STATIC 0x0008 Marked or implicitly static in
source.
ACC_FINAL 0x0010 Marked final in source.
ACC_INTERFACE 0x0200 Was an interface in source.
ACC_ABSTRACT 0x0400 Marked or implicitly abstract in
source.
ACC_SYNTHETIC 0x1000 Declared synthetic; Not present
in the source code.
ACC_ANNOTATION 0x2000 Declared as an annotation type.
ACC_ENUM 0x4000 Declared as an enum type.

All bits of the inner_class_access_flags item not
assigned in Table 4.7 are reserved for future use. They should be
set to zero in generated class files and should be ignored by Java
virtual machine implementations.

The Java virtual machine does not currently check the consistency of the
InnerClasses attribute with any class file actually representing a class or
interface referenced by the attribute.

 */
import classfile.*;

public class InnerClassesAttribute extends AttributeInfo {
    /**
The value of the number_of_classes item indicates the number
of entries in the classes array.
     */
    public short numberOfClasses;

    /**
Every CONSTANT_Class_info entry in the constant_pool table
which represents a class or interface C that is not a package
member must have exactly one corresponding entry in the
classes array.
If a class has members that are classes or interfaces, its
constant_pool table (and hence its InnerClasses attribute)
must refer to each such member, even if that member is not
otherwise mentioned by the class. These rules imply that a nested
class or interface member will have InnerClasses information
for each enclosing class and for each immediate member.
     */
    public ClassEntry[] classes;// [numberOfClasses];

    /**
Each classes array entry contains the following four items:
     */
    public static class ClassEntry {
/**
The value of the inner_class_info_index item must be
zero or a valid index into the constant_pool table. The
constant_pool entry at that index must be a
CONSTANT_Class_info (§4.5.1) structure representing C.
The remaining items in the classes array entry give
information about C.
 */
        public short innerClassInfoIndex;
/**
If C is not a member, the value of the
outer_class_info_index item must be zero. Otherwise,
the value of the outer_class_info_index item must be a
valid index into the constant_pool table, and the entry at
that index must be a CONSTANT_Class_info (§4.5.1)
structure representing the class or interface of which C is a
member.
 */
        public short outerClassInfoIndex;
/**
If C is anonymous, the value of the inner_name_index
item must be zero. Otherwise, the value of the
inner_name_index item must be a valid index into the
constant_pool table, and the entry at that index must be a
CONSTANT_Utf8_info (§4.5.7) structure that represents the
original simple name of C, as given in the source code from
which this class file was compiled.
 */
        public short innerNameIndex;
/**
The value of the inner_class_access_flags item is a
mask of flags used to denote access permissions to and
properties of class or interface C as declared in the source
code from which this class file was compiled. It is used by
compilers to recover the original information when source
code is not available. The flags are shown in Table 4.7.

Table 4.7 Nested class access and property flags
Flag Name Value Meaning
ACC_PUBLIC 0x0001 Marked or implicitly public in
source.
ACC_PRIVATE 0x0002 Marked private in source.
ACC_PROTECTED 0x0004 Marked protected in source.
ACC_STATIC 0x0008 Marked or implicitly static in
source.
ACC_FINAL 0x0010 Marked final in source.
ACC_INTERFACE 0x0200 Was an interface in source.
ACC_ABSTRACT 0x0400 Marked or implicitly abstract in
source.
ACC_SYNTHETIC 0x1000 Declared synthetic; Not present
in the source code.
ACC_ANNOTATION 0x2000 Declared as an annotation type.
ACC_ENUM 0x4000 Declared as an enum type.

All bits of the inner_class_access_flags item not
assigned in Table 4.7 are reserved for future use. They should be
set to zero in generated class files and should be ignored by Java
virtual machine implementations.
 */
        public short innerClassAccessFlags;
    };

    /** Marked or implicitly public in source. */
    public final static short ACC_PUBLIC = 0x0001;
     /** Marked private in source. */
    public final static short ACC_PRIVATE = 0x0002;
     /** Marked protected in source. */
    public final static short ACC_PROTECTED = 0x0004;
     /** Declared static. */
    public final static short ACC_STATIC = 0x0008;
     /** Marked final in source. */
    public final static short ACC_FINAL = 0x0010;
     /** Was an interface in source. */
    public final static short ACC_INTERFACE = 0x0200;
     /** Marked or implicitly abstract in source. */
    public final static short ACC_ABSTRACT = 0x0400;
     /** Declared synthetic; Not present in the source code. */
    public final static short ACC_SYNTHETIC = 0x1000;
     /** Declared as an annotation type. */
    public final static short ACC_ANNOTATION = 0x2000;
     /** Declared as an enum type. */
    public final static short ACC_ENUM = 0x4000;
}
