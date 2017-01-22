package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ébel Zsolt
 */
public class CitiesDialog extends JDialog {

    private JPanel listPanel;
    private JPanel tablePanel;
    private JList list;
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane tScrollPane;
    private DefaultTableCellRenderer rightRenderer;
    private JButton deleteButton;
    private Border listBorder;
    private final Object[] columnNames = {"City name", "District", "Population"};

    public CitiesDialog() {
        setTitle("Country dialog");
        setSize(800, 600);
        setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        setLocationRelativeTo(null);
        add(createListPanel());
        add(createTablePanel());
        setModal(true);
        setResizable(false);
        setVisible(true);
    }

    private JPanel createListPanel() {
        listPanel = new JPanel();

    //JList for countries    
        list = new JList();
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setPreferredSize(new Dimension(200, 560));
        
        listPanel.add(listScrollPane);
        return listPanel;
    }

    private JPanel createTablePanel() {
        tablePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 10));
        tablePanel.setPreferredSize(new Dimension(575, 560));

    // Delete Button
        deleteButton = new JButton("Delete");
        deleteButton.setFocusable(false);

    // JTable with DefaultTableModel
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        table.setPreferredScrollableViewportSize(new Dimension(570, 491));
        table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        tScrollPane = new JScrollPane(table);

        tablePanel.add(deleteButton);
        tablePanel.add(Box.createRigidArea(new Dimension(15, 20)));
        tablePanel.add(tScrollPane);
        return tablePanel;
    }
    
    public int getSelectedIndex() {
        return list.getSelectedIndex();
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public void loadCountryNames(Vector listData) {
        list.setListData(listData);
    }    

    public void fillTableData(Vector cityData) {
        model.addRow(cityData);
    }

    public void clearTable() {
        model.getDataVector().removeAllElements();
    }
    
    public void removeRow(int row) {
        model.removeRow(row);
    }

    @Override
    public void addMouseListener(MouseListener mouseListener) {
        list.addMouseListener(mouseListener);
    }

    public void addDeleteButtonListener(ActionListener actionlistener) {
        deleteButton.addActionListener(actionlistener);
    }

}
