package com.zhaofeng.util;

import java.io.File; 
import java.io.FilenameFilter;

public class MusicFilter implements FilenameFilter{
//过滤歌曲
	public boolean accept(File dir, String filename) {
		return (filename.endsWith(".mp3"));
	}
}
