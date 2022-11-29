package SlangWordDictionary;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

class SlangDictionaryApp extends JPanel implements ItemListener

{
	static JFrame frame;

	private String[] columnNames = new String[2];
	private String[][] dataValues;
	private String[][] historyList;
	String[] answerList = { "A", "B", "C", "D" };

	String[] answers1 = new String[4];
	String[] answers2 = new String[4];
	int userAnswer1;
	int userAnswer2;
	int correctAnswer1;
	int correctAnswer2;

	JTextField definitionField1 = new JTextField();
	JTextField definitionField2 = new JTextField();
	JTextField definitionField3 = new JTextField();
	JTextField definitionField4 = new JTextField();
	JTextField definitionField5 = new JTextField();
	JTextField definitionField6 = new JTextField();
	JTextField definitionField8 = new JTextField();

	JTextField slangWordField4 = new JTextField();
	JTextField slangWordField5 = new JTextField();
	JTextField slangWordField6 = new JTextField();
	JTextField slangWordField8 = new JTextField();


	static JLabel question;
	static JLabel question2;
	static JLabel labelApp;
	JPanel cards;
	JPanel card1;
	JPanel card2;
	JPanel card3;
	JPanel card4;
	JPanel card5;
	JPanel card6;
	JPanel card7;
	JPanel card8;
	JPanel card9;
	JPanel card10;
	
	final static String FEATURE1 = "Search by slang word";
	final static String FEATURE2 = "Search by slang definition";
	final static String FEATURE3 = "View search history";
	final static String FEATURE4 = "Add new slang word";
	final static String FEATURE5 = "Edit slang word";
	final static String FEATURE6 = "Delete slang word";
	final static String FEATURE7 = "Restore backup";
	final static String FEATURE8 = "On this day slang word";
	final static String FEATURE9 = "Guess the definition";
	final static String FEATURE10 = "Guess the slang word";

	SlangWordList slangWord = new SlangWordList();

	public SlangDictionaryApp()

	{
		setBorder(BorderFactory.createEmptyBorder(30, // top
				30, // left
				10, // bottom
				30) // right
		);
		setLayout(new BorderLayout(5, 5));
		columnNames = new String[] { "Slang word", "Definition" };
		dataValues = slangWord.slangWordList();
		labelApp = new JLabel("SLANG DICTIONARY APP");
		JPanel comboBoxPane = new JPanel();
		comboBoxPane.setBorder(BorderFactory.createLineBorder(Color.black));
		String comboBoxItems[] = { FEATURE1, FEATURE2, FEATURE3, FEATURE4, FEATURE5, FEATURE6, FEATURE7, FEATURE8,
				FEATURE9, FEATURE10 };

		JComboBox cb = new JComboBox(comboBoxItems);
		cb.setEditable(false);
		cb.addItemListener(this);
		comboBoxPane.add(cb);

		cards = new JPanel(new CardLayout());

		// card1
		final JPanel card1 = new JPanel(new BorderLayout(20, 20));

		final JPanel topPanel1 = new JPanel();
		topPanel1.setLayout(new FlowLayout());

		JLabel definitionLabel1 = new JLabel("Enter Slang Word");

		definitionField1.setPreferredSize(new Dimension(250, 30));

		final JButton button1 = new JButton("Search");

		topPanel1.add(definitionLabel1);
		topPanel1.add(definitionField1);
		topPanel1.add(button1);

		card1.add(topPanel1, BorderLayout.PAGE_START);

		final JPanel middlePanel1 = new JPanel();
		middlePanel1.setLayout(new FlowLayout());
		final JTable table1 = new JTable();
		table1.setRowHeight(30);
		table1.setDefaultEditor(Object.class, null);
		TableModel model = new myTableModel();
		table1.setModel(model);

		table1.getColumnModel().getColumn(0).setPreferredWidth(60);
		table1.getColumnModel().getColumn(1).setPreferredWidth(300);

		JScrollPane scrollPane1 = new JScrollPane(table1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane1.setSize(600, 600);
		scrollPane1.setPreferredSize(new Dimension(600, 600));
		scrollPane1.setMaximumSize(new Dimension(600, 600));

		middlePanel1.add(scrollPane1);
		card1.add(middlePanel1, BorderLayout.CENTER);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String word = definitionField1.getText();
				if (!word.equals("")) {
					definitionField1.setText("");

					dataValues = slangWord.findDefinition(word);
					if (dataValues.length == 0) {
						JOptionPane.showMessageDialog(frame, "Slang is not in dictionary");
						dataValues = slangWord.slangWordList();
						TableModel model = new myTableModel();
						table1.setModel(model);
					} else {
						TableModel model = new myTableModel();
						table1.setModel(model);
						table1.getColumnModel().getColumn(0).setPreferredWidth(60);
						table1.getColumnModel().getColumn(1).setPreferredWidth(300);
					}
				}

				else {
					JOptionPane.showMessageDialog(frame, "Please enter slang word!");
				}
			}
		});

		// card2
		JPanel card2 = new JPanel(new BorderLayout(20, 20));

		final JPanel topPanel2 = new JPanel();
		topPanel2.setLayout(new FlowLayout());

		JLabel definitionLabel2 = new JLabel("Enter Definition:");
		definitionField2.setPreferredSize(new Dimension(250, 30));

		final JButton  button2 = new JButton("Search");

		topPanel2.add(definitionLabel2);
		topPanel2.add(definitionField2);
		topPanel2.add(button2);

		card2.add(topPanel2, BorderLayout.PAGE_START);

		final JPanel middlePanel2 = new JPanel();
		middlePanel2.setLayout(new FlowLayout());
		final JTable table2 = new JTable();
		table2.setRowHeight(30);
		table2.setModel(model);
		table2.getColumnModel().getColumn(0).setPreferredWidth(60);
		table2.getColumnModel().getColumn(1).setPreferredWidth(300);

		JScrollPane scrollPane2 = new JScrollPane(table2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane2.setSize(600, 600);
		scrollPane2.setPreferredSize(new Dimension(600, 600));
		scrollPane2.setMaximumSize(new Dimension(600, 600));

		middlePanel2.add(scrollPane2);
		card2.add(middlePanel2, BorderLayout.CENTER);

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String word = definitionField2.getText();
				if (!word.equals("")) {
					dataValues = slangWord.findSlangWords(word);
					if (dataValues.length == 0) {
						JOptionPane.showMessageDialog(frame, "Definition is not in dictionary");
						dataValues = slangWord.slangWordList();
						TableModel model = new myTableModel();
						table2.setModel(model);
					} else {
						TableModel model = new myTableModel();
						table2.setModel(model);
						definitionField2.setText("");
						table2.getColumnModel().getColumn(0).setPreferredWidth(60);
						table2.getColumnModel().getColumn(1).setPreferredWidth(300);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Please enter dinition!");
				}
			}
		});

		// card3
		card3 = new JPanel(new BorderLayout(20, 20));

		final JPanel topPanel3 = new JPanel();
		topPanel3.setLayout(new FlowLayout());

		final JButton  button3 = new JButton("View History");

		topPanel3.add(button3);

		card3.add(topPanel3, BorderLayout.PAGE_START);

		final JPanel middlePanel3 = new JPanel();
		middlePanel3.setLayout(new FlowLayout());
		final JTable table3 = new JTable();
		table3.setRowHeight(30);

		JScrollPane scrollPane3 = new JScrollPane(table3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane3.setSize(600, 600);
		scrollPane3.setPreferredSize(new Dimension(600, 600));
		scrollPane3.setMaximumSize(new Dimension(600, 600));

		middlePanel3.add(scrollPane3);
		card3.add(middlePanel3, BorderLayout.CENTER);
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dataValues = slangWord.ViewSearchHistory();
				if (dataValues.length == 0) {
					JOptionPane.showMessageDialog(frame, "No search history");
				} else {
					TableModel model = new myTableModel();
					table3.setModel(model);
					definitionField3.setText("");
					table3.getColumnModel().getColumn(0).setPreferredWidth(60);
					table3.getColumnModel().getColumn(1).setPreferredWidth(300);
				}
			}
		});

		// card4
		card4 = new JPanel(new BorderLayout(20, 20));

		final JPanel topPanel4 = new JPanel();
		topPanel4.setLayout(new GridLayout(0, 2, 10, 10));

		JLabel slangWordLabel4 = new JLabel("Enter Slang Word");
		topPanel4.add(slangWordLabel4);
		topPanel4.add(slangWordField4);
		slangWordField4.setPreferredSize(new Dimension(250, 30));

		final JPanel middlePanel4 = new JPanel(new FlowLayout());
		// slangWordLabel4.setPreferredSize(new Dimension(250,30));
		slangWordLabel4.setHorizontalAlignment(JLabel.RIGHT);

		JLabel definitionLabel4 = new JLabel("Enter Definition ");
		topPanel4.add(definitionLabel4);
		topPanel4.add(definitionField4);
		definitionField4.setPreferredSize(new Dimension(250, 30));
		definitionLabel4.setHorizontalAlignment(JLabel.RIGHT);

		final JButton  button4 = new JButton("Add");
		middlePanel4.add(button4);

		card4.add(topPanel4, BorderLayout.PAGE_START);
		card4.add(middlePanel4, BorderLayout.CENTER);
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String word = slangWordField4.getText();
				String definition = definitionField4.getText();
				if (!word.equals("") && !definition.equals("")) {
					slangWordField4.setText("");
					definitionField4.setText("");
					if (slangWord.checkSlangWordExist(word)) {
						Object[] options = { "Overwrite", "Duplicate", "Cancel" };
						int type = JOptionPane.showOptionDialog(frame, "Slang word is exist, Choose type of edit:",
								"Add new slang word", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
								null, options, options[2]);
						if (type == 0) {
							slangWord.AddNewWord(0, word, definition);
	
						} else if (type == 1) {
							slangWord.AddNewWord(1, word, definition);

						}
					} else {
						slangWord.add(word, definition);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Please enter slang word and dinitition");
				}
			}
		});

		// card5
		card5 = new JPanel(new BorderLayout(20, 20));

		final JPanel topPanel5 = new JPanel();
		topPanel5.setLayout(new GridLayout(0, 2, 10, 10));

		JLabel slangWordLabel5 = new JLabel("Enter Slang Word");
		topPanel5.add(slangWordLabel5);
		topPanel5.add(slangWordField5);
		slangWordField5.setPreferredSize(new Dimension(250, 30));
		slangWordLabel5.setHorizontalAlignment(JLabel.RIGHT);

		JLabel definitionLabel5 = new JLabel("Enter Definition  ");
		topPanel5.add(definitionLabel5);
		topPanel5.add(definitionField5);
		definitionField5.setPreferredSize(new Dimension(250, 30));
		definitionLabel5.setHorizontalAlignment(JLabel.RIGHT);

		final JPanel middlePanel5 = new JPanel(new FlowLayout());
		final JButton  button5 = new JButton("Edit");
		middlePanel5.add(button5);

		card5.add(topPanel5, BorderLayout.PAGE_START);
		card5.add(middlePanel5, BorderLayout.CENTER);
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String word = slangWordField5.getText();
				String definition = definitionField5.getText();
				if (!word.equals("") && !definition.equals("")) {
					slangWordField5.setText("");
					definitionField5.setText("");
					if (slangWord.checkSlangWordExist(word)) {
						slangWord.editSlangWord(word, definition);
						JOptionPane.showMessageDialog(frame, "Edit slang word successful!");
					} else {
						JOptionPane.showMessageDialog(frame, "Slang word is not in dictionary");
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Please enter slang word and dinitition");
				}
			}
		});

		// card6
		card6 = new JPanel(new BorderLayout(20, 20));

		final JPanel topPanel6 = new JPanel();
		topPanel6.setLayout(new GridLayout(0, 2, 10, 10));

		JLabel slangWordLabel6 = new JLabel("Enter Slang Word");
		topPanel6.add(slangWordLabel6);
		topPanel6.add(slangWordField6);
		slangWordField6.setPreferredSize(new Dimension(200, 30));
		slangWordLabel6.setHorizontalAlignment(JLabel.RIGHT);

		final JPanel middlePanel6 = new JPanel(new FlowLayout());
		final JButton  button6 = new JButton("Delete");
		middlePanel6.add(button6);
		card6.add(topPanel6, BorderLayout.PAGE_START);
		card6.add(middlePanel6, BorderLayout.CENTER);

		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String word = slangWordField6.getText();
				if (!word.equals("")) {
					slangWordField6.setText("");
					boolean exist = slangWord.checkSlangWordExist(word);
					if (exist) {
						if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete slang word?",
								"WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							slangWord.deleteSlangWord(true, word);
						} else {
							slangWord.deleteSlangWord(false, word);
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Slang is not in dictionary");
					}
					definitionField6.setText("");
				}

				else {
					JOptionPane.showMessageDialog(frame, "Please enter slang word and dinitition");
				}
			}
		});

		// card7
		card7 = new JPanel(new BorderLayout(20, 20));

		final JPanel topPanel7 = new JPanel();
		topPanel7.setLayout(new FlowLayout());
		JLabel label7 = new JLabel("Reset the original slang words list");
		final JButton  button7 = new JButton("Reset");
		label7.setHorizontalAlignment(JLabel.CENTER);

		topPanel7.add(button7);

		card7.add(label7, BorderLayout.PAGE_START);
		card7.add(topPanel7, BorderLayout.CENTER);

		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to restore backup?", "WARNING",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					slangWord.RestoreBackup(true);
				} else {
					slangWord.RestoreBackup(false);
				}
			}
		});

		// card8
		card8 = new JPanel(new BorderLayout(20, 20));

		final JPanel topPanel8 = new JPanel();
		topPanel8.setLayout(new GridLayout(0, 2, 10, 10));

		JLabel slangWordLabel8 = new JLabel("Slang Word");
		topPanel8.add(slangWordLabel8);
		topPanel8.add(slangWordField8);
		slangWordField8.setPreferredSize(new Dimension(250, 30));
		slangWordField8.setEditable(false);

		JLabel definitionLabel8 = new JLabel("Definition");
		topPanel8.add(definitionLabel8);
		topPanel8.add(definitionField8);
		definitionField8.setPreferredSize(new Dimension(250, 30));
		definitionField8.setEditable(false);

		definitionLabel8.setHorizontalAlignment(JLabel.RIGHT);
		slangWordLabel8.setHorizontalAlignment(JLabel.RIGHT);

		final JPanel middlePanel8 = new JPanel(new FlowLayout());
		final JButton button8 = new JButton("Random");
		middlePanel8.add(button8);
		card8.add(topPanel8, BorderLayout.PAGE_START);
		card8.add(middlePanel8, BorderLayout.CENTER);

		button8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String[] randomWord = slangWord.RandomWord();
				slangWordField8.setText(randomWord[0]);
				definitionField8.setText(randomWord[1]);

			}
		});

		// card 9
		card9 = new JPanel(new BorderLayout(10, 20));

		final JPanel topPanel9 = new JPanel();
		topPanel9.setLayout(new GridLayout(0, 1, 10, 0));

		answers1 = slangWord.GuessDefinition();

		String questionLabel = slangWord.getQuestion();

		question = new JLabel("Slang word: " + questionLabel);
		JPanel panel0 = new JPanel(new FlowLayout());
		JPanel panel1 = new JPanel(new FlowLayout());
		JPanel panel2 = new JPanel(new FlowLayout());
		JPanel panel3 = new JPanel(new FlowLayout());
		JPanel panel4 = new JPanel(new FlowLayout());

		final JLabel answerNumber1 = new JLabel("A. ");
		final JLabel answerNumber2 = new JLabel("B. ");
		final JLabel answerNumber3 = new JLabel("C. ");
		final JLabel answerNumber4 = new JLabel("D. ");

		final JTextField answer1 = new JTextField(answers1[0]);
		final JTextField answer2 = new JTextField(answers1[1]);
		final JTextField answer3 = new JTextField(answers1[2]);
		final JTextField answer4 = new JTextField(answers1[3]);

		answer1.setCaretPosition(0);
		answer2.setCaretPosition(0);
		answer3.setCaretPosition(0);
		answer4.setCaretPosition(0);

		answer1.setBorder(null);
		answer2.setBorder(null);
		answer3.setBorder(null);
		answer4.setBorder(null);

		final JButton  button9 = new JButton("Next Question");

		topPanel9.add(Box.createRigidArea(new Dimension(5, 50)));
		panel0.add(question);

		panel1.add(answerNumber1);
		panel1.add(answer1);
		panel2.add(answerNumber2);
		panel2.add(answer2);
		panel3.add(answerNumber3);
		panel3.add(answer3);
		panel4.add(answerNumber4);
		panel4.add(answer4);

		topPanel9.add(panel0);
		topPanel9.add(panel1);
		topPanel9.add(panel2);
		topPanel9.add(panel3);
		topPanel9.add(panel4);

		answerNumber1.setHorizontalAlignment(JLabel.LEFT);
		answerNumber2.setHorizontalAlignment(JLabel.LEFT);
		answerNumber3.setHorizontalAlignment(JLabel.LEFT);
		answerNumber4.setHorizontalAlignment(JLabel.LEFT);

		answer1.setPreferredSize(new Dimension(500, 30));
		answer1.setEditable(false);
		answer2.setPreferredSize(new Dimension(500, 30));
		answer2.setEditable(false);
		answer3.setPreferredSize(new Dimension(500, 30));
		answer3.setEditable(false);
		answer4.setPreferredSize(new Dimension(500, 30));
		answer4.setEditable(false);

		final JPanel middlePanel9 = new JPanel(new FlowLayout());

		JLabel answerLabel = new JLabel("Your answer");

		final JComboBox comboBox1 = new JComboBox(answerList);

		JButton answerBtn1 = new JButton("Answer");
		middlePanel9.add(answerLabel);
		middlePanel9.add(comboBox1);
		middlePanel9.add(answerBtn1);
		middlePanel9.add(button9);

		comboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userAnswer1 = comboBox1.getSelectedIndex();
			}
		});

		card9.add(topPanel9, BorderLayout.PAGE_START);
		card9.add(middlePanel9, BorderLayout.CENTER);

		button9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				answers1 = slangWord.GuessDefinition();

				answer1.setText(answers1[0]);
				answer2.setText(answers1[1]);
				answer3.setText(answers1[2]);
				answer4.setText(answers1[3]);

				String questionLabel = slangWord.getQuestion();
				question.setText("Slang word: " + questionLabel);
			}
		});

		answerBtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				correctAnswer1 = slangWord.getCorrectAnswer();
				if (userAnswer1 == correctAnswer1) {
					JOptionPane.showMessageDialog(frame, "Correct Answer!");
				} else {
					JOptionPane.showMessageDialog(frame,
							"You haven't answered yet! , the correct answer is:  " + answers1[correctAnswer1]);
				}

				comboBox1.setSelectedIndex(0);
			}
		});
		// card 10
		card10 = new JPanel(new BorderLayout(10, 20));

		final JPanel topPanel10 = new JPanel();
		topPanel10.setLayout(new GridLayout(0, 1, 10, 0));

		JPanel panel9 = new JPanel(new FlowLayout());
		JPanel panel5 = new JPanel(new FlowLayout());
		JPanel panel6 = new JPanel(new FlowLayout());
		JPanel panel7 = new JPanel(new FlowLayout());
		JPanel panel8 = new JPanel(new FlowLayout());

		final JLabel answerNumber5 = new JLabel("A. ");
		final JLabel answerNumber6 = new JLabel("B. ");
		final JLabel answerNumber7 = new JLabel("C. ");
		final JLabel answerNumber8 = new JLabel("D. ");

		answers2 = slangWord.GuessSlangWord();

		questionLabel = slangWord.getQuestion();
		question2 = new JLabel("Definition: " + questionLabel);
		question2.setHorizontalAlignment(JLabel.CENTER);

		final JTextField answer5 = new JTextField(answers2[0]);
		final JTextField answer6 = new JTextField(answers2[1]);
		final JTextField answer7 = new JTextField(answers2[2]);
		final JTextField answer8 = new JTextField(answers2[3]);

		answer5.setCaretPosition(0);
		answer6.setCaretPosition(0);
		answer7.setCaretPosition(0);
		answer8.setCaretPosition(0);

		answer5.setBorder(null);
		answer6.setBorder(null);
		answer7.setBorder(null);
		answer8.setBorder(null);

		final JButton  button10 = new JButton("Next Question");
		topPanel10.add(Box.createRigidArea(new Dimension(5, 50)));

		panel9.add(question2);
		panel5.add(answerNumber5);
		panel5.add(answer5);
		panel6.add(answerNumber6);
		panel6.add(answer6);
		panel7.add(answerNumber7);
		panel7.add(answer7);
		panel8.add(answerNumber8);
		panel8.add(answer8);

		topPanel10.add(panel9);
		topPanel10.add(panel5);
		topPanel10.add(panel6);
		topPanel10.add(panel7);
		topPanel10.add(panel8);

		answerNumber5.setHorizontalAlignment(JLabel.LEFT);
		answerNumber6.setHorizontalAlignment(JLabel.LEFT);
		answerNumber7.setHorizontalAlignment(JLabel.LEFT);
		answerNumber8.setHorizontalAlignment(JLabel.LEFT);

		answer5.setPreferredSize(new Dimension(300, 30));
		answer5.setEditable(false);
		answer6.setPreferredSize(new Dimension(300, 30));
		answer6.setEditable(false);
		answer7.setPreferredSize(new Dimension(300, 30));
		answer7.setEditable(false);
		answer8.setPreferredSize(new Dimension(300, 30));
		answer8.setEditable(false);

		final JPanel middlePanel10 = new JPanel(new FlowLayout());
		JLabel answerLabel2 = new JLabel("Your answer");

		// String[] answerList = {"A", "B", "C", "D"};
		final JComboBox comboBox2 = new JComboBox(answerList);
		JButton answerBtn2 = new JButton("Answer");

		middlePanel10.add(answerLabel2);
		middlePanel10.add(comboBox2);
		middlePanel10.add(answerBtn2);
		middlePanel10.add(button10);

		comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userAnswer2 = comboBox2.getSelectedIndex();
			}
		});

		card10.add(topPanel10, BorderLayout.PAGE_START);
		card10.add(middlePanel10, BorderLayout.CENTER);
		button10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				answers2 = slangWord.GuessSlangWord();
				answer5.setText(answers2[0]);
				answer6.setText(answers2[1]);
				answer7.setText(answers2[2]);
				answer8.setText(answers2[3]);

				String questionLabel = slangWord.getQuestion();
				question2.setText("Definition: " + questionLabel);
			}
		});

		answerBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				correctAnswer2 = slangWord.getCorrectAnswer();
				if (userAnswer2 == correctAnswer2) {
					JOptionPane.showMessageDialog(frame, "Correct Answer!");
				} else {
					JOptionPane.showMessageDialog(frame,
							"You haven't answered yet! , the correct answer is:  " + answers2[correctAnswer2]);
				}

				comboBox2.setSelectedIndex(0);
			}
		});

		cards.add(card1, FEATURE1);
		cards.add(card2, FEATURE2);
		cards.add(card3, FEATURE3);
		cards.add(card4, FEATURE4);
		cards.add(card5, FEATURE5);
		cards.add(card6, FEATURE6);
		cards.add(card7, FEATURE7);
		cards.add(card8, FEATURE8);
		cards.add(card9, FEATURE9);
		cards.add(card10, FEATURE10);

		add(labelApp, BorderLayout.PAGE_START);
		add(comboBoxPane, BorderLayout.LINE_START);
		add(cards, BorderLayout.CENTER);
		add(Box.createRigidArea(new Dimension(10, 0)), BorderLayout.LINE_END);
	}

	public static void changeFont(Component component, Font font) {
		component.setFont(font);
		if (component instanceof Container) {
			for (Component child : ((Container) component).getComponents()) {
				changeFont(child, font);
			}
		}
	}

	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout) (cards.getLayout());
		cl.show(cards, (String) evt.getItem());
	}

	public class myTableModel extends DefaultTableModel {
		myTableModel()

		{

			super(dataValues, columnNames);
		}

		public boolean isCellEditable(int row, int cols)

		{
			return true;
		}

	}

	public static void createAndShowGUI() {
		frame = new JFrame("demo1");

		JFrame.setDefaultLookAndFeelDecorated(true);
		SlangDictionaryApp newContentPane = new SlangDictionaryApp();
		frame.setContentPane(newContentPane);
		changeFont(newContentPane, new Font("Serif", Font.PLAIN, 18));
		labelApp.setFont(new Font("Serif", Font.PLAIN, 40));
		question.setFont(new Font("Serif", Font.PLAIN, 20));
		question2.setFont(new Font("Serif", Font.PLAIN, 20));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.pack();
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}