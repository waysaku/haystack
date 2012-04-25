package jp.co.haystack;

import java.io.File;

public interface HayStack {
	public void save(String path, byte[] bytes);
	public File load(String path);
	public boolean exist(String path);
}
