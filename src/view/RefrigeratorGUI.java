package view;

import controller.Refrigerator;
import model.Food;
import save.SaveProcess; // 클래스 이름 변경에 따른 import 수정

import javax.swing.*;
import java.awt.*;

public class RefrigeratorGUI {
	private JFrame frame;
	private DefaultListModel<String> listModel;
	private JList<String> foodListDisplay;
	private Refrigerator refrigerator;

	public RefrigeratorGUI(Refrigerator refrigerator) {
		this.refrigerator = refrigerator;
		refrigerator.loadFromFile();

		initializeGUI();
		updateDisplay();
	}

	private void initializeGUI() {
		frame = new JFrame("냉장고 관리 시스템");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		listModel = new DefaultListModel<>();
		foodListDisplay = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(foodListDisplay);
		frame.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
		JButton addButton = new JButton("추가");
		JButton deleteButton = new JButton("삭제");
		JButton searchButton = new JButton("검색");
		JButton sortButton = new JButton("정렬");
		JButton saveButton = new JButton("현재 데이터 저장");
		JButton exportButton = new JButton("다른 이름으로 저장");

		buttonPanel.add(addButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(searchButton);
		buttonPanel.add(sortButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(exportButton);

		frame.add(buttonPanel, BorderLayout.SOUTH);

		addButton.addActionListener(e -> addFood());
		deleteButton.addActionListener(e -> deleteFood());
		searchButton.addActionListener(e -> searchFood());
		sortButton.addActionListener(e -> sortFood());
		saveButton.addActionListener(e -> saveData());
		exportButton.addActionListener(e -> exportData());

		frame.setVisible(true);
	}

	private void addFood() {
		String name = JOptionPane.showInputDialog(frame, "식품 이름:");
		String quantityStr = JOptionPane.showInputDialog(frame, "수량:");
		String date = JOptionPane.showInputDialog(frame, "유통기한 (yyyy-MM-dd):");

		try {
			int quantity = Integer.parseInt(quantityStr);
			Food food = new Food(name, quantity, date);
			refrigerator.addFood(food);
			updateDisplay();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "수량은 숫자여야 합니다.");
		}
	}

	private void deleteFood() {
		String name = JOptionPane.showInputDialog(frame, "삭제할 식품 이름:");
		if (name != null) {
			refrigerator.removeFood(name);
			updateDisplay();
		}
	}

	private void searchFood() {
		String name = JOptionPane.showInputDialog(frame, "검색할 식품 이름:");
		Food food = refrigerator.searchFood(name);
		if (food != null) {
			JOptionPane.showMessageDialog(frame, "검색 결과:\n" + food.toString());
		} else {
			JOptionPane.showMessageDialog(frame, "해당 식품이 없습니다.");
		}
	}

	private void sortFood() {
		refrigerator.sortByExpirationDate();
		updateDisplay();
		JOptionPane.showMessageDialog(frame, "유통기한 순으로 정렬되었습니다.");
	}

	private void saveData() {
		refrigerator.saveToFile();
		JOptionPane.showMessageDialog(frame, "데이터가 저장되었습니다.");
	}

	private void exportData() {
		String filename = JOptionPane.showInputDialog(frame, "내보낼 파일 이름을 입력하세요 (예: export.txt):");
		if (filename != null && !filename.trim().isEmpty()) {
			SaveProcess.exportDataToFile(refrigerator.getFoodList(), "data/" + filename);
			JOptionPane.showMessageDialog(frame, "데이터가 내보내졌습니다: " + filename);
		} else {
			JOptionPane.showMessageDialog(frame, "파일 이름이 잘못되었습니다.");
		}
	}

	private void updateDisplay() {
		listModel.clear();
		for (Food food : refrigerator.getFoodList()) {
			listModel.addElement(food.toString());
		}
	}
}
