package util;

import classfile.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class ExportColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {

    ClassFile cf;
    int memberType;
    int row;
    JButton renderButton;
    JButton editButton;
    String text;

    public ExportColumn(ClassFile cf, int memberType) {
        this.cf = cf;
        this.memberType = memberType;
        renderButton = new JButton();
        editButton = new JButton();
        editButton.setFocusPainted(false);
        editButton.addActionListener(this);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (hasFocus) {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        } else if (isSelected) {
            renderButton.setForeground(table.getSelectionForeground());
            renderButton.setBackground(table.getSelectionBackground());
        } else {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        }

        renderButton.setText((value == null) ? "" : value.toString());
        return renderButton;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        text = (value == null) ? "" : value.toString();
        editButton.setText(text);
        return editButton;
    }

    public Object getCellEditorValue() {
        return text;
    }

    public void actionPerformed(ActionEvent e) {
        fireEditingStopped();

        Object obj;
        if (memberType == 0) {
            obj = cf.fields[row].attributes;
        } else if (memberType == 1) {
            obj = cf.methods[row].attributes;
        } else {
            obj = cf.attributes[row];
        }
        String objText = DebugUtil.getObjectText(obj, 0);
        TextFrame.showFrame(objText);
    }
}

