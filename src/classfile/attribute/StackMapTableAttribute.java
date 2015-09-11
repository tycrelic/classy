package classfile.attribute;
//to do
import classfile.*;

/**
4.8.4 The StackMapTable Attribute
The stack map attribute is a variable-length attribute in the attributes table of a Code
attribute. The name of the attribute is StackMapTable. This attribute is used during
the process of verification by typechecking (§4.11.1).
A stack map attribute consists of zero or more stack map frames. Each stack map
frame specifies (either explicitly or implicitly) a bytecode offset, the verification
types (§4.11.1) for the local variables, and the verification types for the operand
stack.
The type checker deals with and manipulates the expected types of a method’s
local variables and operand stack. Throughout this section, a location refers to
either a single local variable or to a single operand stack entry.
We will use the terms stack map frame and type state interchangeably to describe
a mapping from locations in the operand stack and local variables of a method to
verification types. We will usually use the term stack map frame when such a
mapping is provided in the class file, and the term type state when the mapping is
inferred by the type checker.
If a method’s Code attribute does not have a StackMapTable attribute, it has an
implicit stack map attribute. This implicit stack map attribute is equivalent to a
StackMapTable attribute with number_of_entries equal to zero. A method’s
Code attribute may have at most one StackMapTable attribute, otherwise a
ClassFormatError is thrown.
The format of the stack map in the class file is given below.stack_map {
// attribute StackMapTable
u2 attribute_name_index;
u4 attribute_length
u2 number_of_entries;
stack_map_frame entries[number_of_entries];
}
Each stack_map_frame structure specifies the type state at a particular bytecode
offset. Each frame type specifies (explicitly or implicitly) a value, offset_delta,
that is used to calulate the actual bytecode offset at which it applies. The bytecode
offset at which the frame applies is given by adding 1 + offset_delta to the
offset of the previous frame, unless the previous frame is the initial frame of the
method, in which case the bytecode offset is offset_delta.
By using an offset delta rather than the actual bytecode offset we
ensure, by definition, that stack map frames are in the correctly sorted
order. Furthermore, by consistently using the formula offset_delta
+ 1 for all explicit frames, we guarantee the absence of duplicates.
All frame types, even full_frame, rely on the previous frame for
some of their semantics. This raises the question of what is the very first
frame? The initial frame is implicit, and computed from the method
descriptor. See the Prolog code for
methodInitialStackMapFrame.
The stack_map_frame structure consists of a one-byte tag followed by zero or
more bytes, giving more information, depending upon the tag.
A stack map frame may belong to one of several frame types
union stack_map_frame {
same_frame;
same_locals_1_stack_item_frame;
same_locals_1_stack_item_frame_extended;
chop_frame;
same_frame_extended;
append_frame;
full_frame;
}
 */
public class StackMapTableAttribute extends AttributeInfo {
    public short numberOfEntries;
    public StackMapFrame entries;//[number_of_entries];


}
