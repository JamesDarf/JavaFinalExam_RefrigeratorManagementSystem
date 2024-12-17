package controller;

import model.Food;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Refrigerator {
	private ArrayList<Food> foodList = new ArrayList<>();
	private final String DATA_DIR = "data";
	private final String DATA_FILE = DATA_DIR + "/fridge_data.txt";

	public Refrigerator() {
		// 데이터 폴더 생성
		try {
			Files.createDirectories(Paths.get(DATA_DIR));
		} catch (IOException e) {
			System.out.println("data 폴더 생성 실패: " + e.getMessage());
		}
	}

	// 식품 추가
	public void addFood(Food food) {
		foodList.add(food);
	}

	// 식품 삭제
	public void removeFood(String name) {
		foodList.removeIf(food -> food.getName().equalsIgnoreCase(name));
	}

	// 식품 검색
	public Food searchFood(String name) {
		for (Food food : foodList) {
			if (food.getName().equalsIgnoreCase(name)) {
				return food;
			}
		}
		return null;
	}

	// 유통기한 기준으로 정렬
	public void sortByExpirationDate() {
		foodList.sort(Comparator.comparing(Food::getExpirationDate));
	}

	// 식품 목록 반환
	public ArrayList<Food> getFoodList() {
		return foodList;
	}

	// 파일 저장
	public void saveToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
			for (Food food : foodList) {
				writer.write(food.getName() + "," + food.getQuantity() + "," + food.getExpirationDate());
				writer.newLine();
			}
			System.out.println("데이터가 저장되었습니다.");
		} catch (IOException e) {
			System.out.println("파일 저장 오류: " + e.getMessage());
		}
	}

	// 파일 불러오기
	public void loadFromFile() {
		foodList.clear();
		try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 3) { // 데이터의 일관성을 확인
					foodList.add(new Food(parts[0], Integer.parseInt(parts[1]), parts[2]));
				}
			}
			System.out.println("데이터가 불러와졌습니다.");
		} catch (FileNotFoundException e) {
			System.out.println("저장된 데이터가 없습니다. 새로 시작합니다.");
		} catch (IOException e) {
			System.out.println("파일 읽기 오류: " + e.getMessage());
		}
	}
}
