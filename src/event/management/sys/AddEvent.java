package event.management.sys;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AddEvent extends JFrame implements ActionListener {

    private JTextField tfEventName, tfOrganizerName, tfLocation, tfBudget;
    private JDateChooser dcStartDate, dcEndDate;
    private JComboBox<String> cbStatus;
    private JLabel lblEventId;
    private JButton add, back;

    private Random random = new Random();
    private int number = random.nextInt(999999);

    public AddEvent() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Add Event Details");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        JLabel[] labels = {
                new JLabel("Event Name"), new JLabel("Organizer Name"), new JLabel("Start Date"),
                new JLabel("End Date"), new JLabel("Location"), new JLabel("Budget"),
                new JLabel("Status"), new JLabel("Event Id")
        };

        int[] yPositions = {150, 200, 250, 300, 350, 400, 450, 500};

        for (int i = 0; i < labels.length; i++) {
            labels[i].setBounds(50, yPositions[i], 150, 30);
            labels[i].setFont(new Font("serif", Font.PLAIN, 20));
            add(labels[i]);
        }

        tfEventName = createTextField(200, 150);
        tfOrganizerName = createTextField(200, 200);
        tfLocation = createTextField(200, 350);
        tfBudget = createTextField(200, 400);
        dcStartDate = createDateChooser(200, 250);
        dcEndDate = createDateChooser(200, 300);
        cbStatus = createStatusComboBox(200, 450);
        lblEventId = createLabel(String.valueOf(number), 200, 500);

        add = createButton("Add Details", 250, 550);
        back = createButton("Back", 450, 550);

        setSize(900, 600);
        setLocation(300, 50);
        setVisible(true);
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 150, 30);
        add(textField);
        return textField;
    }

    private JDateChooser createDateChooser(int x, int y) {
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(x, y, 150, 30);
        add(dateChooser);
        return dateChooser;
    }

    private JComboBox<String> createStatusComboBox(int x, int y) {
        String[] statusOptions = {"Planned", "Ongoing", "Completed"};
        JComboBox<String> comboBox = new JComboBox<>(statusOptions);
        comboBox.setBackground(Color.WHITE);
        comboBox.setBounds(x, y, 150, 30);
        add(comboBox);
        return comboBox;
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 30);
        label.setFont(new Font("serif", Font.PLAIN, 20));
        add(label);
        return label;
    }

    private JButton createButton(String label, int x, int y) {
        JButton button = new JButton(label);
        button.setBounds(x, y, 150, 40);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        add(button);
        return button;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            addEventDetails();
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Home();
        }
    }

    private void addEventDetails() {
        String eventName = tfEventName.getText();
        String organizerName = tfOrganizerName.getText();
        String startDate = ((JTextField) dcStartDate.getDateEditor().getUiComponent()).getText();
        String endDate = ((JTextField) dcEndDate.getDateEditor().getUiComponent()).getText();
        String location = tfLocation.getText();
        String budget = tfBudget.getText();
        String status = (String) cbStatus.getSelectedItem();
        String eventId = lblEventId.getText();

        try {
            conn conn = new conn();
            String query = "insert into events values('" + eventName + "', '" + organizerName + "', '" + startDate + "', '" + endDate + "', '" + location + "', '" + budget + "', '" + status + "', '" + eventId + "')";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Details added successfully");
            setVisible(false);
            new Home();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AddEvent();
    }
}

