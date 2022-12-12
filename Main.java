package project;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class Main extends JFrame implements ActionListener {

	JTextArea area;
	JMenu user;
	JMenu menu;

	JMenuItem HomeMI;
	JMenuItem LoginMI;
	JMenuItem StockControlMI;
	JMenuItem ShoppingBasketMI;
	JMenuItem ExitMI;

	// Stock control GUI items
	JMenuItem addItem;
	JMenuItem viewItems;

	JTextArea displayStock;
	JScrollPane scrollStock;

	String[] ITEMTYPE = { "Luxury", "Gift", "Essential" };
	JComboBox comboBoxJC;
	JComboBox itemList;

	CardLayout cards;
	JPanel display;

	int itemID = 0;

	ArrayList<Item> stock;
	int quantityOfLuxury = 0;
	int quantityOfGift = 0;
	int quantityOfEssential = 0;

	// Shopping Basket
	JTextField vatRate;
	JButton addToBasket;
	JButton calculate;
	JComboBox itemsListCB;
	JSpinner quantitySpinner;
	JTextArea basketDisplay;
	JScrollPane scroll;
	JLabel basketTotal;
	JComboBox itemtypeCB;
	ArrayList<String> basket;

	public Main() {

		super("Welcome home page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		stock = new ArrayList<Item>();
		basket = new ArrayList<String>();

		JFrame frame = new JFrame("Welcome home page");
		JPanel home = new JPanel(new GridLayout(3, 2));

		Container c = frame.getContentPane();

		JLabel text1 = new JLabel();

		text1.setText("Welcome To our Homepage");

		home.add(text1);

//Create Card Layout 
		cards = new CardLayout();
		display = new JPanel();
		display.setLayout(cards);

		JTextArea customerTxt = new JTextArea("Login/Register");
		JTextArea shoppingBasketTxt = new JTextArea("Shopping Basket");

		JPanel loginJP = new JPanel();
		loginJP.add(customerTxt);
		JPanel stockJP = new JPanel();
		stockJP.setLayout(new BorderLayout());

//Code begins for shopping basket 
		JPanel ShoppingBasketJP = new JPanel(new GridLayout(1, 2));

//Left panel code 
		JPanel westPanel = new JPanel(new GridLayout(5, 1));
//Declare Swing items 

		itemtypeCB = new JComboBox(ITEMTYPE);
		itemtypeCB.addActionListener(this);

		vatRate = new JTextField("VAT RATE : 20%");
		vatRate.setEditable(false);

		itemsListCB = new JComboBox();

		quantitySpinner = new JSpinner();
		quantitySpinner.setModel(new SpinnerNumberModel(0, 0, 999, 1));
		JFormattedTextField tf = ((JSpinner.DefaultEditor) quantitySpinner.getEditor()).getTextField();
		tf.setEditable(false);

		westPanel.add(itemtypeCB);
		westPanel.add(vatRate);
		westPanel.add(itemsListCB);
		westPanel.add(quantitySpinner);

		addToBasket = new JButton("Add to basket");
		calculate = new JButton("Calculate");

		addToBasket.addActionListener(this);
		calculate.addActionListener(this);

		JPanel basketButtons = new JPanel(new GridLayout(1, 2));
		basketButtons.add(addToBasket);
		basketButtons.add(calculate);

		westPanel.add(basketButtons);

//Right panel code
//eastPanel.setBackground(Color.blue);
		JPanel eastPanel = new JPanel(new BorderLayout());

		JPanel basketLabels = new JPanel(new GridLayout(1, 2));
		basketLabels.add(new JLabel("Item name"));
		basketLabels.add(new JLabel("Quantity"));

		basketDisplay = new JTextArea();
		basketDisplay.setEditable(false);
		scroll = new JScrollPane(basketDisplay);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		basketTotal = new JLabel("Basket Total : ");

		eastPanel.add(basketLabels, BorderLayout.NORTH);
		eastPanel.add(scroll, BorderLayout.CENTER);
		eastPanel.add(basketTotal, BorderLayout.SOUTH);

		ShoppingBasketJP.add(westPanel);
		ShoppingBasketJP.add(eastPanel);

//ShoppingBasketJP.add(shoppingBasketTxt);

		JMenu Home = new JMenu("Home");
		JMenu Login = new JMenu("Login");
		JMenu Stock = new JMenu("Stock");
		JMenu ShoppingBasket = new JMenu("ShoppingBasket");
		JMenu Exit = new JMenu("Exit");

		HomeMI = new JMenuItem("Home");
		LoginMI = new JMenuItem("Login");
//StockControlMI = new JMenuItem("Stock Control");
		ShoppingBasketMI = new JMenuItem("Shopping Basket");
		ExitMI = new JMenuItem("Exit");

		addItem = new JMenuItem("Add Items");
		viewItems = new JMenuItem("View Items");

		HomeMI.addActionListener(this);
		LoginMI.addActionListener(this);
		// StockControlMI.addActionListener(this);
		ShoppingBasketMI.addActionListener(this);
		ExitMI.addActionListener(this);
		addItem.addActionListener(this);
		viewItems.addActionListener(this);

		Home.add(HomeMI);
		Login.add(LoginMI);
		// Stock.add(StockControlMI);
		Stock.add(addItem);
		Stock.add(viewItems);
		ShoppingBasket.add(ShoppingBasketMI);
		Exit.add(ExitMI);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(Home);
		menuBar.add(Login);
		menuBar.add(Stock);
		menuBar.add(ShoppingBasket);
		menuBar.add(Exit);
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
		frame.setSize(1000, 500);

		JTextArea stockTxt = new JTextArea("Welcome to the Stock Control HomePage");
		stockTxt.setEditable(false);

		stockJP.add(stockTxt, BorderLayout.NORTH);

		// add to card layout
		display.add(home, "home"); // home panel
		display.add(loginJP, "login");
		display.add(stockJP, "stock");
		display.add(ShoppingBasketJP, "ShoppingBasket");

		frame.add(display);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == itemtypeCB) {
			String itemType = (String) itemtypeCB.getSelectedItem();
			if (itemType.equals("Luxury")) {
				// set vat rate
				vatRate.setText("VAT RATE : 20%");
				// set items combo box
				try {

					itemsListCB.removeAllItems();
					String[] tempArray = getSpecificItems(itemType);
					for (String s : tempArray) {
						itemsListCB.addItem(s);
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// reset quantity spinner
			} else if (itemType.equals("Gift")) {
				// set vat rate
				vatRate.setText("VAT RATE : 5%");
				// set items combo box
				try {
					itemsListCB.removeAllItems();
					String[] tempArray = getSpecificItems(itemType);
					for (String s : tempArray) {
						itemsListCB.addItem(s);
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// reset quantity spinner
			} else {
				// set vat rate
				vatRate.setText("VAT RATE : 10%");
				// set items combo box
				try {
					itemsListCB.removeAllItems();
					String[] tempArray = getSpecificItems(itemType);
					for (String s : tempArray) {
						itemsListCB.addItem(s);
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// reset quantity spinner
			}
		}
		if (e.getSource() == addToBasket) {
			String itemTest = (String) itemsListCB.getSelectedItem();
			if (itemTest != null) {
				String itemName = itemTest.split(" : ")[0];

				int quantity = (int) quantitySpinner.getValue();
				if (quantity == 0) {
					JOptionPane.showMessageDialog(this, "Error, Quantity must be greater than 0", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {

					String itemInfoDisplay = itemName + "\t\t\t x" + quantity + "\n";
					String itemInfoArray = itemName + ":" + quantity;
					basketDisplay.append(itemInfoDisplay);

					basket.add(itemInfoArray);
					quantitySpinner.setValue(0);

				}
			}

		}
		if (e.getSource() == calculate) {
			double totalCost = 0;

			for (String s : basket) {
				String itemName = s.split(":")[0];
				int quantity = Integer.parseInt(s.split(":")[1]);
				for (Item x : stock) {
					if (itemName.equals(x.getItemName())) {
						if (x.getItemType().equals("Luxury")) {
							// lux = 50 + 20% = 60
							totalCost += 60 * quantity;
						} else if (x.getItemType().equals("Gift")) {
							// gift = 20 + 5% = 21
							totalCost += 21 * quantity;
						} else {
							// ess = 30 + 10% = 33
							totalCost += 33 * quantity;
						}
					}
				}
			}

			String amount = "Basket Total : " + totalCost;
			basketTotal.setText(amount);
		}

		if (e.getSource() == HomeMI) {
			cards.show(display, "home");
		}
		if (e.getSource() == LoginMI) {
			cards.show(display, "customer");
		}
		if (e.getSource() == StockControlMI) {
			cards.show(display, "stock");
		}
		if (e.getSource() == ShoppingBasketMI) {
			try {
				quantitySpinner.setValue(0);
				itemtypeCB.setSelectedItem("Luxury");
				String[] tempArray1 = getSpecificItems("Luxury");
				for (String s : tempArray1) {
					itemsListCB.addItem(s);
				}
			} catch (ParseException exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			}
			cards.show(display, "ShoppingBasket");
		}
		if (e.getSource() == ExitMI) {
			System.exit(0);
		}
		if (e.getSource() == addItem) {
			cards.show(display, "stock");
			itemID++;

			JTextField itemName = new JTextField(); // String itemName;

			comboBoxJC = new JComboBox(ITEMTYPE);
			comboBoxJC.addActionListener(this); // itemType

			JFormattedTextField expirationDate = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy")); // expirationDate
			String itemNameTxt = "Please enter item name";
			String itemTypeTxt = "Please select item type";
			String expirationDateTxt = "Please enter expiration date (DD/MM/YYYY format only)";
			String itemIdTxt = "Item ID will be : " + itemID; // + customerId;

			int result = JOptionPane.showOptionDialog(this,
					new Object[] { itemNameTxt, itemName, itemTypeTxt, comboBoxJC, expirationDateTxt, expirationDate,
							itemIdTxt },
					"Add Item to Stock Control", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
					null);

			if (result == JOptionPane.OK_OPTION) // if ok is selected
			{
				String itemNameStr = itemName.getText();
				String itemTypeStr = (String) comboBoxJC.getSelectedItem();
				String expirationDateStr = expirationDate.getText();

				if (isValidDate(expirationDateStr) && itemNameStr.length() != 0) {
					double price = 0;
					if (itemTypeStr == "Luxury") {
						price = 50.00;
						quantityOfLuxury++;
					} else if (itemTypeStr == "Gift") {
						price = 20.00;
						quantityOfGift++;
					} else {
						price = 30.00;
						quantityOfEssential++;
					}

					Item i = new Item(itemNameStr, itemTypeStr, expirationDateStr, itemID, price);
					stock.add(i);

				} else {
					JOptionPane.showMessageDialog(null, "Invalid Input entered \nItem was not added",
							"Invalid Input Message", JOptionPane.ERROR_MESSAGE);
				}

			}

			if (result == JOptionPane.CANCEL_OPTION) // if cancel is selected
			{
				JOptionPane.showMessageDialog(null, "Item was not added to stock", "Cancel Message",
						JOptionPane.PLAIN_MESSAGE);

			}

		}
		if (e.getSource() == viewItems) {
			cards.show(display, "stock");
			String[] items = getItems();
			itemList = new JComboBox(items);

			Object[] options = { "DELETE", "CANCEL" };
			int result = JOptionPane.showOptionDialog(this, new Object[] { "Stock Inventory", itemList },
					"View Items in Stock Control", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);

			if (result == 0) {
				if (stock.size() != 0) {

					String itemTest = (String) itemList.getSelectedItem();
					String itemName = itemTest.split(" : ")[0];
					String itemDate = itemTest.split(" : ")[1];

					for (Iterator<Item> itr = stock.iterator(); itr.hasNext();) {
						Item currentItem = itr.next();
						if (currentItem.getItemName().equals(itemName)
								&& currentItem.getExpirationDate().equals(itemDate)) {
							itr.remove();

							if (currentItem.getItemType().equals("Luxury")) {
								quantityOfLuxury--;
								System.out.print("Current quantity of Luxury = " + quantityOfLuxury);
							} else if (currentItem.getItemType().equals("Gift")) {
								quantityOfGift--;
								System.out.print("Current quantity of Gift = " + quantityOfGift);
							} else {
								quantityOfEssential--;
								System.out.print("Current quantity of Essential = " + quantityOfEssential);
							}
						}

					}

				}
			}

		}
	}

	public boolean isValidDate(String dateStr) {
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public String[] getItems() {
		ArrayList<String> arrlist = new ArrayList<String>();
		for (Item x : stock) {
			String nameAndDate = x.getItemName() + " : " + x.getExpirationDate();
			arrlist.add(nameAndDate);
		}
		// Convert the Arraylist to array

		String[] arrayOfItems = new String[arrlist.size()];
		arrayOfItems = arrlist.toArray(arrayOfItems);

		return arrayOfItems;
	}

	public String[] getSpecificItems(String type) throws ParseException {
		ArrayList<String> arrlist = new ArrayList<String>();
		for (Item x : stock) {
			if (x.getItemType().equals(type) && inDate(x)) {
				String nameAndDate = x.getItemName() + " : " + x.getExpirationDate();
				arrlist.add(nameAndDate);

			}

		}
		// Convert the Arraylist to array

		String[] arrayOfItems = new String[arrlist.size()];
		arrayOfItems = arrlist.toArray(arrayOfItems);

		return arrayOfItems;
	}

	public boolean inDate(Item item) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(item.getExpirationDate());

		if (date.before(Calendar.getInstance().getTime())) {
			return false;
		} else {
			return true;
		}
	}

	public static void main(String[] args) {
		// Create and set up the window.
		Main frmcb = new Main();
	}

}