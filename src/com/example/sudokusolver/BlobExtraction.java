package com.example.sudokusolver;

import android.graphics.Bitmap;
import android.graphics.Color;

public class BlobExtraction {

	private Bitmap bmp;
	private int[][]label;
	
	public BlobExtraction(Bitmap bitmap){
		bmp = bitmap;
		label = new int[bitmap.getWidth()][bitmap.getHeight()];
	}
	
	public void blobExtract() {
		int currentLabel = 1;

		for (int i = 0; i < bmp.getWidth(); i++) {
			for (int j = 0; j < bmp.getHeight(); j++) {
				if (bmp.getPixel(i, j) == Color.WHITE) {
					int num = checkNeighbors(i, j);
					if (num <= 0) {
						label[i][j] = currentLabel;
						currentLabel++;
					} else {
						label[i][j] = num;
					}
				}
			}
		}
	}

	public int checkNeighbors(int x, int y) {
		int min = 10000;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (i != x && j != y && bmp.getPixel(i, j) == Color.WHITE) {
					if (label[i][j] > 0) {
						if (label[j][j] < min) {
							min = label[i][j];
						}
					}
				}
			}
		}
		if (min == 10000)
			return 0;
		else
			return min;
	}
}