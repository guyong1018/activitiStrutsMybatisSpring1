package com.xjgzinfo.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipInputStream;

import org.apache.struts2.ServletActionContext;

import com.xjgzinfo.activiti.ActivitiManager;
import com.xjgzinfo.common.BaseAction;

@SuppressWarnings("serial")
public class DeployAction extends BaseAction {

	private File file;
	private String fileFileName;
	private String fileFileContentType;
	private ActivitiManager activitiManager;

	public ActivitiManager getActivitiManager() {
		return activitiManager;
	}

	public void setActivitiManager(ActivitiManager activitiManager) {
		this.activitiManager = activitiManager;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileFileContentType() {
		return fileFileContentType;
	}

	public void setFileFileContentType(String fileFileContentType) {
		this.fileFileContentType = fileFileContentType;
	}

	public void deployFLow() throws Exception {

//		String path = ServletActionContext.getRequest().getRealPath("/upload");

		try {
			File f = this.getFile();
			if (!this.getFileFileName().endsWith(".zip")) {
				response.getWriter().write("1");
				return;
			}
//			String deployFileName = path + File.separator
//					+ this.getFileFileName();
			FileInputStream inputStream = new FileInputStream(f);
			ZipInputStream zipin = new ZipInputStream(inputStream);
			activitiManager.deployFlow(zipin);
			response.getWriter().write("2");
			inputStream.close();
			zipin.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("3");
		}
	}

}