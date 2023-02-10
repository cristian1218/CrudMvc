/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelo.UsuarioD;
import modelo.Usuario;
import vista.Vista;

public class Controlador implements ActionListener {

    UsuarioD dao = new UsuarioD();
    Usuario u = new Usuario();
    Vista vista = new Vista();
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlador(Vista v) {
        this.vista = v;
        this.vista.btnSave.addActionListener(this);
        this.vista.btnEdit.addActionListener(this);
        this.vista.btnDelete.addActionListener(this);
        this.vista.btnUpdate.addActionListener(this);
        list(vista.table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnDelete) {
            delete();
            list(vista.table);
            clear();
        }
        if (e.getSource() == vista.btnUpdate) {
            update();
            list(vista.table);
            clear();

        }
        if (e.getSource() == vista.btnEdit) {
            int row = vista.table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione un registro de la tabla");
            } else {
                int id = Integer.parseInt((String) vista.table.getValueAt(row, 0).toString());
                String name = (String) vista.table.getValueAt(row, 1);
                String mail = (String) vista.table.getValueAt(row, 2);
                String tel = (String) vista.table.getValueAt(row, 3);
                String rol = (String) vista.table.getValueAt(row, 4);

                vista.txtId.setText("" + id);
                vista.txtName.setText(name);
                vista.txtMail.setText(mail);
                vista.txtPhone.setText(tel);
                vista.txtRol.setText(rol);

            }
        }
        if (e.getSource() == vista.btnSave) {
            add();
            list(vista.table);
            clear();
        }
    }

    void clear() {
        vista.txtId.setText("");
        vista.txtName.setText("");
        vista.txtPhone.setText("");
        vista.txtMail.setText("");
        vista.txtRol.setText("");
        vista.txtName.requestFocus();
    }

    public void delete() {
        int row = vista.table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un registro de la tabla");
        } else {
            int id = Integer.parseInt((String) vista.table.getValueAt(row, 0).toString());
            dao.delete(id);
            System.out.println("El Reusltaod es" + id);
            JOptionPane.showMessageDialog(vista, "Eliminado correctamente");
        }
        clearTable();
    }

    public void add() {
        String name = vista.txtName.getText();
        String mail = vista.txtMail.getText();
        String tel = vista.txtPhone.getText();
        String rol = vista.txtRol.getText();
        
        u.setName(name);
        u.setMail(mail);
        u.setTel(tel);
        u.setRol(rol);
        
        int r = dao.add(u);
        if (r == 1 && !name.isEmpty() && !mail.isEmpty() && !tel.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Usuario guardado correctamente");
        } else {
            JOptionPane.showMessageDialog(vista, "Error, llene todos los datos");
        }
        clearTable();
    }

    public void update() {
        if (vista.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "Error de registro");
        } else {
            int id = Integer.parseInt(vista.txtId.getText());
            String name = vista.txtName.getText();
            String mail = vista.txtMail.getText();
            String tel = vista.txtPhone.getText();
            String rol = vista.txtRol.getText();
            u.setId(id);
            u.setName(name);
            u.setMail(mail);
            u.setTel(tel);
            u.setRol(rol);
            
            int r = dao.update(u);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "Usuario editado correctamente");
            } else {
                JOptionPane.showMessageDialog(vista, "Error");
            }
        }
        clearTable();
    }

    public void list(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Usuario> lista = dao.list();
        Object[] objeto = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getName();
            objeto[2] = lista.get(i).getMail();
            objeto[3] = lista.get(i).getTel();
            objeto[4] = lista.get(i).getRol();
            modelo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }


    void clearTable() {
        for (int i = 0; i < vista.table.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
}
