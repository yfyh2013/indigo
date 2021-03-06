/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IndigoObjectWithPropertiesViewPanel.java
 *
 * Created on May 10, 2011, 2:55:54 AM
 */
package com.ggasoftware.indigo.controls;

import com.ggasoftware.indigo.IndigoObject;
import com.ggasoftware.indigo.IndigoRenderer;
import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rybalkin
 */
public class IndigoObjectWithPropertiesViewPanel extends javax.swing.JPanel
{
   private IndigoRenderer _indigo_renderer;
   private IndigoObject _obj;

   /** Creates new form IndigoObjectWithPropertiesViewPanel */
   public IndigoObjectWithPropertiesViewPanel ()
   {
      initComponents();
      message_field.setVisible(false);
   }
   
   public void setDividerLocation (int position)
   {
      splitter.setDividerLocation(position);
   }

   public int getDividerLocation ()
   {
      return splitter.getDividerLocation();
   }

   public void setInformationMessage (String message)
   {
      message_field.setText(message);
      message_field.setVisible(message != null && !message.equals(""));
   }
   
   public String getInformationMessage ()
   {
      return message_field.getText();
   }
   
   /** Creates new form CellFrame */
   public void setObject (IndigoObject chem_obj, IndigoRenderer indigo_renderer)
   {
      this._indigo_renderer = indigo_renderer;
      this._obj = chem_obj;

      if (_obj != null && !_obj.hasCoord())
      {
         _obj = chem_obj.clone();
         _obj.layout();
      }
      mol_view_panel.setIndigoObject(_obj, _indigo_renderer);

      _fillPropertyTable();

      mol_view_panel.setSize(mol_view_panel.getPreferredSize());

      // Set table properties to readonly
      JTextField tx = new JTextField();
      tx.setEditable(false);
      property_table.setDefaultEditor(String.class, new DefaultCellEditor(tx));
   }

   public String getSelectedPropertyName ()
   {
      int sel_row = getSelectedPropertyIndex();
      if (sel_row == -1)
         return null;
      return (String)property_table.getValueAt(sel_row, 0);
   }
   
   public int getSelectedPropertyIndex ()
   {
      return property_table.getSelectedRow();
   }
   
   public void setSelectedPropertyIndex (int index)
   {
      property_table.setRowSelectionInterval(index, index);
   }

   public int getPropertiesCount ()
   {
      return property_table.getRowCount();
   }
   
   public void addCustomProperty (int where, String prop_name, String prop_value)
   {
      DefaultTableModel model = (DefaultTableModel)property_table.getModel();
      model.insertRow(where, new String[]
                 {
                    prop_name, prop_value
                 });
   }
   
   private void _fillPropertyTable ()
   {
      DefaultTableModel model = (DefaultTableModel)property_table.getModel();
      while (model.getRowCount() != 0)
         model.removeRow(0);

      if (_obj == null)
         return;

      for (IndigoObject prop : _obj.iterateProperties())
      {
         String prop_name = prop.name();
         String prop_value = _obj.getProperty(prop.name());

         model.addRow(new String[]
                 {
                    prop_name, prop_value
                 });
      }
   }
   
   
   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      splitter = new javax.swing.JSplitPane();
      subpanel = new javax.swing.JPanel();
      mol_view_panel = new com.ggasoftware.indigo.controls.IndigoObjectViewPanel();
      message_field = new javax.swing.JTextField();
      property_scroll_panel = new javax.swing.JScrollPane();
      property_table = new javax.swing.JTable();

      splitter.setBorder(null);
      splitter.setDividerLocation(200);
      splitter.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
      splitter.setResizeWeight(0.8);
      splitter.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

      subpanel.setLayout(new javax.swing.BoxLayout(subpanel, javax.swing.BoxLayout.PAGE_AXIS));

      mol_view_panel.setPreferredSize(new java.awt.Dimension(400, 1960));

      javax.swing.GroupLayout mol_view_panelLayout = new javax.swing.GroupLayout(mol_view_panel);
      mol_view_panel.setLayout(mol_view_panelLayout);
      mol_view_panelLayout.setHorizontalGroup(
         mol_view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 400, Short.MAX_VALUE)
      );
      mol_view_panelLayout.setVerticalGroup(
         mol_view_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 180, Short.MAX_VALUE)
      );

      subpanel.add(mol_view_panel);

      message_field.setEditable(false);
      subpanel.add(message_field);

      splitter.setLeftComponent(subpanel);

      property_table.setModel(new javax.swing.table.DefaultTableModel(
         new Object [][] {

         },
         new String [] {
            "Property name", "Value"
         }
      ) {
         Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class
         };

         public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
         }
      });
      property_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
      property_table.getTableHeader().setReorderingAllowed(false);
      property_scroll_panel.setViewportView(property_table);

      splitter.setBottomComponent(property_scroll_panel);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addComponent(splitter, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addComponent(splitter, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
      );
   }// </editor-fold>//GEN-END:initComponents
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JTextField message_field;
   private com.ggasoftware.indigo.controls.IndigoObjectViewPanel mol_view_panel;
   private javax.swing.JScrollPane property_scroll_panel;
   private javax.swing.JTable property_table;
   private javax.swing.JSplitPane splitter;
   private javax.swing.JPanel subpanel;
   // End of variables declaration//GEN-END:variables
}
