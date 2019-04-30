package com.test.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.drive.DriveApp;
import org.springframework.social.google.api.drive.DriveFile;
import org.springframework.social.google.api.drive.DriveFilesPage;
import org.springframework.social.google.api.drive.UploadParameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/drive")
public class DriveController {

	private final Google google;

	@Autowired
	public DriveController(Google google) {
		this.google = google;
	}


	@ResponseBody
	@RequestMapping(value = "/listApps", method = GET)
	public List<DriveApp> listApps() {
		return google.driveOperations().getApps();
	}

	@ResponseBody
	@RequestMapping(value = "/createFolder/{folderName}", method = POST)
	public void createFolder(@PathVariable String folderName) {
		google.driveOperations().createFolder("root", folderName);
	}

	@ResponseBody
	@RequestMapping(value = "/listFiles", method = GET)
	public DriveFilesPage getFilesInDrive(@RequestParam(required = false) String pageToken) {
		return google.driveOperations().getRootFiles(pageToken);
	}

	@ResponseBody
	@RequestMapping(value = "/deleteFile", method = GET)
	public void deleteFile(@RequestParam String fileID) {
		google.driveOperations().delete(fileID);
	}

	@ResponseBody
	@RequestMapping(value = "/uploadFile", method = GET)
	public void uploadFile() {
//			Resource resource = new FileSystemResource("/resources/images/test.png");  // any Resource implementation can be used
		Resource resource = new FileSystemResource("/resources/images/test.png");  // any Resource implementation can be used

		DriveFile metadata = DriveFile.builder()  // use this builder to set metadata
				.setTitle("My File")
				.build();

		UploadParameters parameters = new UploadParameters();
		// call setters to modify upload parameters
		google.driveOperations().upload(resource, metadata, parameters);
	}


}
