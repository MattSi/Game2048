package com.propig.game.Game2048.view;

import java.awt.Color;

public class BlockColor {

	private static Color[][] colors = {
			{new Color(238,228,218), new Color(119, 110, 101) },
			{new Color(237,240,208), new Color(119, 110, 101) },
			{new Color(242,177,121), new Color(249, 246, 242) }, //8
			{new Color(245,149, 99), new Color(249, 246, 242) }, //16
			{new Color(246,124, 95), new Color(249, 246, 242) }, //32
			{new Color(246, 94, 59), new Color(249, 246, 242) }, //64
			{new Color(237,207,114), new Color(249, 246, 242) }, // 128
			{new Color(237,204, 97), new Color(249, 246, 242) },
			{new Color(237,200, 80), new Color(249, 246, 242) }, // 512
			{new Color(237,197, 63), new Color(249, 246, 242) }, // 1024
			{new Color(237,197, 46), new Color(249, 246, 242) },   // 2048
			{new Color(237,197, 46), new Color(249, 246, 242) }   // 2048
	}; 
	public static Color getBlockColor(int number, boolean isBackground){		
		if(isBackground){
			return colors[number][0];
		}
		return colors[number][1];
	}
}
