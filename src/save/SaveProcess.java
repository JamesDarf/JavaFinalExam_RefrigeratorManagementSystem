package save;

import model.Food;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveProcess {
	public static void exportDataToFile(List<Food> foodList, String filename) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			for (Food food : foodList) {
				writer.write(food.getName() + "," + food.getQuantity() + "," + food.getExpirationDate());
				writer.newLine();
			}
			System.out.println("데이터가 저장되었습니다: " + filename);
		} catch (IOException e) {
			System.out.println("파일 저장 중 오류: " + e.getMessage());
		}
	}
}
