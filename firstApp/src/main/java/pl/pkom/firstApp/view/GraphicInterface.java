package pl.pkom.firstApp.view;

import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import pl.pkom.firstApp.businessLogic.Controller;

public class GraphicInterface extends JFrame{
    
    private Controller controller;
    private final int frameWidth = 700;
    private final int frameHeight = 600;
    private JPanel controlPanel;
    private JPanel dataPanel;
    private JSplitPane splitPane;
    private JLabel lblName;
    private JLabel lblSurname;  
    private JLabel lblCountry;    
    private JLabel lblAddress;   
    private JLabel lblAge;  
    private JButton btnSelect;  
    private JButton btnInsert;  
    private JButton btnUpdate; 
    private JButton btnDelete;
    private JButton btnClearAllFields;
    private GroupLayout gl_controlPanel;
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private JTextField textFieldName;
    private JTextField textFieldSurname;
    private JTextField textFieldCountry;
    private JTextField textFieldAddress;
    private JTextField textFieldAge;
    
    private Vector tableData = new Vector();
    private final Vector columnNames = new Vector();
    
    private int SelectedID;
    
    public GraphicInterface() {
        
        controller = new Controller(this);
        this.setTitle("First Java GUI");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        controlPanel = new JPanel();
        controlPanel.setSize(frameWidth, 300);
        controlPanel.setVisible(true);

        dataPanel = new JPanel();
        dataPanel.setSize(frameWidth, 300);
        dataPanel.setVisible(true);

        splitPane = new JSplitPane();
        splitPane.setSize(frameWidth, frameHeight);
        splitPane.setDividerSize(5);
        splitPane.setDividerLocation(300);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setLeftComponent(controlPanel);
        
        lblName = new JLabel("Name");
        
        lblSurname = new JLabel("Surname");
        
        lblCountry = new JLabel("Country");
        
        lblAddress = new JLabel("Address");
        
        lblAge = new JLabel("Age");
        
        btnSelect = new JButton("Select");
        btnSelect.addActionListener(evt -> new BtnSelectListener(this, controller).actionPerformed(evt));
        
        btnInsert = new JButton("Insert");
        btnInsert.addActionListener(evt -> new BtnInsertListener(this, controller).actionPerformed(evt));
        
        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(evt -> new BtnUpdateListener(this, controller).actionPerformed(evt));
        
        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(evt -> new BtnDeleteListener(this, controller).actionPerformed(evt));
        
        btnClearAllFields = new JButton("Clear all fields");
        btnClearAllFields.addActionListener(evt -> new BtnClearListener(this).actionPerformed(evt));
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent evt) {
                int windowClosingOption = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz zamknąć program?", "Window Closing", JOptionPane.YES_NO_OPTION);

                if (windowClosingOption == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                } else {
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
            }
        });
        
        textFieldName = new JTextField();
        textFieldName.setColumns(10);
        
        textFieldSurname = new JTextField();
        textFieldSurname.setColumns(10);
        
        textFieldCountry = new JTextField();
        textFieldCountry.setColumns(10);
        
        textFieldAddress = new JTextField();
        textFieldAddress.setColumns(10);
        
        textFieldAge = new JTextField();
        textFieldAge.setColumns(10);
        
        GroupLayout gl_controlPanel = new GroupLayout(controlPanel);
        gl_controlPanel.setHorizontalGroup(
            gl_controlPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_controlPanel.createSequentialGroup()
                    .addGap(60)
                    .addGroup(gl_controlPanel.createParallelGroup(Alignment.TRAILING)
                        .addComponent(lblSurname)
                        .addComponent(lblCountry)
                        .addComponent(lblAddress)
                        .addComponent(lblAge)
                        .addComponent(lblName))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(textFieldAge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGroup(gl_controlPanel.createSequentialGroup()
                            .addGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING)
                                .addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textFieldCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textFieldSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textFieldAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(45)
                            .addGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING, false)
                                .addGroup(gl_controlPanel.createSequentialGroup()
                                    .addGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSelect, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                                    .addGap(24)
                                    .addGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnInsert, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)))
                                .addComponent(btnClearAllFields, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGap(102))
        );
        gl_controlPanel.setVerticalGroup(
            gl_controlPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_controlPanel.createSequentialGroup()
                    .addGap(33)
                    .addGroup(gl_controlPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblName)
                        .addComponent(btnSelect)
                        .addComponent(btnInsert))
                    .addGap(18)
                    .addGroup(gl_controlPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblSurname)
                        .addComponent(textFieldSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdate)
                        .addComponent(btnDelete))
                    .addGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_controlPanel.createSequentialGroup()
                            .addGap(24)
                            .addComponent(lblCountry))
                        .addGroup(gl_controlPanel.createSequentialGroup()
                            .addGap(18)
                            .addComponent(textFieldCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_controlPanel.createSequentialGroup()
                            .addGap(24)
                            .addComponent(lblAddress)
                            .addGap(21)
                            .addGroup(gl_controlPanel.createParallelGroup(Alignment.BASELINE)
                                .addComponent(lblAge)
                                .addComponent(textFieldAge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(gl_controlPanel.createSequentialGroup()
                            .addGap(18)
                            .addGroup(gl_controlPanel.createParallelGroup(Alignment.BASELINE)
                                .addComponent(textFieldAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnClearAllFields))))
                    .addGap(94))
        );
        
        controlPanel.setLayout(gl_controlPanel);
        splitPane.setRightComponent(dataPanel);
         
        columnNames.add("ID");
        columnNames.add("Name");
        columnNames.add("Surname");
        columnNames.add("Country");
        columnNames.add("Address");
        columnNames.add("Age");
        
        tableModel = new DefaultTableModel(tableData, columnNames);
        dataPanel.setLayout(new GridLayout(0, 1, 0, 0));
        
        table = new JTable(tableModel);
        table.setCellSelectionEnabled(true);
        table.setDefaultEditor(Object.class, null);
        table.addMouseListener(new RowSelectionListener(this, table) { 
            public void mouseClicked(final MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        dataPanel.add(scrollPane);
        
        this.getContentPane().add(splitPane);
        
    }
    
    public void showInfoDialog(final String message) {
        JOptionPane optionPane = new JOptionPane();
        JOptionPane.showMessageDialog(this, message);
    }
    
    public void showWarningDialog(final String message) {
        JOptionPane optionPane = new JOptionPane();
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }
    
    public boolean chooseOption(final String message, final String title) {

        int option = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }
    
    public void showResultInTable(final ResultSet resultSet) throws SQLException {
 
        Vector values = new Vector();
        
        do {
            Vector row = new Vector();
            for (int i=1; i < 7; i++) {
                row.add(resultSet.getString(i));
            }   
            values.add(row); 
        } while(resultSet.next());
        
        tableModel.setDataVector(values, columnNames);
    } 
    
    public String getName() {
        return textFieldName.getText();
    }
    
    public String getSurname() {
        return textFieldSurname.getText();
    }
    
    public String getCountry() {
        return textFieldCountry.getText();
    }
    
    public String getAddress() {
        return textFieldAddress.getText();
    }
    
    public String getAge() {
        return textFieldAge.getText();
    }

    public void setTextFieldName(final String name) {
        textFieldName.setText(name);
    }
    
    public void setTextFieldSurname(final String surname) {
        textFieldSurname.setText(surname);
    }
    
    public void setTextFieldCountry(final String country) {
        textFieldCountry.setText(country);
    }
    
    public void setTextFieldAddress(final String address) {
        textFieldAddress.setText(address);
    }
    
    public void setTextFieldAge(final String age) {
        textFieldAge.setText(age);
    }

    /**
     * @return the selectedID
     */
    public int getSelectedID() {
        return SelectedID;
    }

    /**
     * @param selectedID the selectedID to set
     */
    public void setSelectedID(int selectedID) {
        SelectedID = selectedID;
    }
}
