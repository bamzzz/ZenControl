package com.wubydax.romcontrol;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by bamzzz on 20 Des 2015.
 */

public class DropdownHeaderImage extends Activity
{
	Button btnPick;
	Button btnSet;
	ImageView ivGalImg;
	Bitmap bmp;
	private File wallpaperImage;
	private File wallpaperTemporary;
	private File wallpaperImageBlur;
	private File wallpaperTemporaryBlur;
	private FileOutputStream Pathtmp;
	private FileChannel TempIn;
	private FileChannel TempOut;
	private FileOutputStream PathtmpBlur;
	private FileChannel TempInBlur;
	private FileChannel TempOutBlur;
	public String SetGambar = "dropdown_header_bg_img";
	public String SetGambarBlur = "dropdown_header_blur_bg_img";

/*
	private void initViews(Context context, AttributeSet attrs) {
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.ImagePickerPreference, 0, 0);

		try {
			// get the text and colors specified using the names in attrs.xml
			ImageFor = a.getString(R.styleable.ImagePickerPreference_ImageFor);
		} finally {
			a.recycle();
		}
	}
*/

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_picker_fragment);
		//String SetGambar = ImageFor;
		//wallpaperImage = new File(this.getFilesDir() + "/dropdown_bg_img_" + System.currentTimeMillis());
		wallpaperImage = new File(this.getFilesDir() + "/" + SetGambar + "_" + System.currentTimeMillis());
		wallpaperTemporary = new File(this.getCacheDir() + "/gambar.tmp"); //new FileOutputStream
		wallpaperImageBlur = new File(this.getFilesDir() + "/" + SetGambarBlur + "_" + System.currentTimeMillis());
		wallpaperTemporaryBlur = new File(this.getCacheDir() + "/gambar_blur.tmp"); //new FileOutputStream
		btnPick = (Button)findViewById(R.id.btnPick);
		btnSet = (Button)findViewById(R.id.btnSet);
		ivGalImg = (ImageView)findViewById(R.id.bamzzzImage);

		ivGalImg.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplicationContext(), "Making blurred Image...",
						Toast.LENGTH_SHORT).show();
				Intent photoPickerIntent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				photoPickerIntent.setType("image/*");
				startActivityForResult(photoPickerIntent, 2);
				//Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nature);
				//Bitmap blurredBitmap = blur(bitmap);
				//ivGalImg.setImageBitmap(blurredBitmap);
			}
		});

		btnPick.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				openGallery();
			}
		});

		btnSet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ApplyImage();
			}
		});
	}

	private void openGallery()
	{
		Intent photoPickerIntent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		photoPickerIntent.setType("image/*");
		startActivityForResult(photoPickerIntent, 1);
	}

	private void ApplyImage() {
		try {
			this.TempIn = new FileInputStream(this.wallpaperTemporary.toString()).getChannel();
			this.TempInBlur = new FileInputStream(this.wallpaperTemporaryBlur.toString()).getChannel();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			this.TempOut = new FileOutputStream(this.wallpaperImage.toString()).getChannel();
			this.TempOutBlur = new FileOutputStream(this.wallpaperImageBlur.toString()).getChannel();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			try {
				this.TempIn.transferTo(0L, this.TempIn.size(), this.TempOut);
				this.TempInBlur.transferTo(0L, this.TempInBlur.size(), this.TempOutBlur);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (this.TempIn != null) {
				try {
					this.TempIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (this.TempOut != null) {
				try {
					this.TempOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (this.TempInBlur != null) {
				try {
					this.TempInBlur.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (this.TempOutBlur != null) {
				try {
					this.TempOutBlur.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//String SetGambar = "dropdown_bg_img";
			String str = Settings.System.getString(getContentResolver(), SetGambar);
			String strBlur = Settings.System.getString(getContentResolver(), SetGambarBlur);
			//String str = Settings.System.getString(getContentResolver(), "dropdown_header_bg_img");
			if (str != null) {
				File localFile = new File(Uri.parse(str).getPath());
				if ((localFile != null) && (localFile.exists())) {
					localFile.delete();
				}
			}
			if (strBlur != null) {
				File localFile2 = new File(Uri.parse(strBlur).getPath());
				if ((localFile2 != null) && (localFile2.exists())) {
					localFile2.delete();
				}
			}
			if (wallpaperTemporary != null) {
				this.wallpaperTemporary.renameTo(wallpaperImage);
				this.wallpaperImage.setReadable(true, false);
				//String SetGambar = "dropdown_header_bg_img";
				Settings.System.putString(getContentResolver(), SetGambar, wallpaperImage.toString());
				Toast.makeText(this, "Apply Image Success :)", Toast.LENGTH_SHORT).show();
			}
			if (wallpaperTemporaryBlur != null) {
				this.wallpaperTemporaryBlur.renameTo(wallpaperImageBlur);
				this.wallpaperImageBlur.setReadable(true, false);
				//String SetGambar = "dropdown_header_bg_img";
				Settings.System.putString(getContentResolver(), SetGambarBlur, wallpaperImageBlur.toString());
				//Toast.makeText(this, "Apply Image Blur Success :)", Toast.LENGTH_SHORT).show();
			}
			else {
				Toast.makeText(this, "Apply Image Failed :(", Toast.LENGTH_SHORT).show();
			}
		}
		finally
		{
			if (this.TempIn != null) {
				try {
					this.TempIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (this.TempOut != null) {
				try {
					this.TempOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (this.TempInBlur != null) {
				try {
					this.TempInBlur.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (this.TempInBlur != null) {
				try {
					this.TempInBlur.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		finish();
	}


	@Override
	protected void onActivityResult(int requestCode, int resultcode, Intent intent)
	{
		super.onActivityResult(requestCode, resultcode, intent);

		if (requestCode == 1)
		{
			//Toast.makeText(this, "REQ 1", Toast.LENGTH_SHORT).show();
			if (intent != null && resultcode == RESULT_OK) {
				//Toast.makeText(this, "Result OK", Toast.LENGTH_SHORT).show();
				Uri selectedImage = intent.getData();
				String[] filePathColumn = {MediaStore.Images.Media.DATA};
				//filePathColumn[0] = "_data";
				Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
				assert cursor != null;
				cursor.moveToFirst();
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String filePath = cursor.getString(columnIndex);
				cursor.close();
				if(bmp != null && !bmp.isRecycled())
				{
					bmp = null;
				}
				bmp = BitmapFactory.decodeFile(filePath);
				try {
					Pathtmp = new FileOutputStream(this.getCacheDir() + "/gambar.tmp");
					PathtmpBlur = new FileOutputStream(this.getCacheDir() + "/gambar_blur.tmp");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				Bitmap.createScaledBitmap(bmp, 520, 520, false).compress(Bitmap.CompressFormat.JPEG, 90, Pathtmp);
				Bitmap blurredBitmap = blur(bmp);
				Bitmap.createScaledBitmap(blurredBitmap, 520, 520, false).compress(Bitmap.CompressFormat.JPEG, 90, PathtmpBlur);
				//ivGalImg.setBackgroundResource(0);
				//ivGalImg.setBackgroundResource(R.drawable.dialog_bg);
				ivGalImg.setImageBitmap(bmp);
				//wallpaperTemporary.setWritable(true, false);
				//wallpaperTemporary.setReadable(true, false);
				//Toast.makeText(this, filePath, Toast.LENGTH_SHORT).show();
			}

			else
			{
				Log.d("Status:", "Photopicker canceled");
			}
		}
		if (requestCode == 2)
		{
			Toast.makeText(this, "REQ 2", Toast.LENGTH_SHORT).show();
			if (intent != null && resultcode == RESULT_OK) {
				//Toast.makeText(this, "Result OK", Toast.LENGTH_SHORT).show();
				Uri selectedImage = intent.getData();
				String[] filePathColumn = {MediaStore.Images.Media.DATA};
				//filePathColumn[0] = "_data";
				Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
				cursor.moveToFirst();
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String filePath = cursor.getString(columnIndex);
				cursor.close();
				if(bmp != null && !bmp.isRecycled())
				{
					bmp = null;
				}
				bmp = BitmapFactory.decodeFile(filePath);
				try {
					Pathtmp = new FileOutputStream(this.getCacheDir() + "/gambar.tmp");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				//Bitmap.createScaledBitmap(bmp, 520, 520, false).compress(Bitmap.CompressFormat.JPEG, 90, Pathtmp);
				//Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nature);
				Bitmap blurredBitmap = blur(bmp);
				Bitmap.createScaledBitmap(blurredBitmap, 520, 520, false).compress(Bitmap.CompressFormat.JPEG, 90, Pathtmp);
				//ivGalImg.setImageBitmap(blurredBitmap);
				//ivGalImg.setBackgroundResource(0);
				//ivGalImg.setBackgroundResource(R.drawable.dialog_bg);
				ivGalImg.setImageBitmap(blurredBitmap);
				//wallpaperTemporary.setWritable(true, false);
				//wallpaperTemporary.setReadable(true, false);
				Toast.makeText(this, filePath, Toast.LENGTH_SHORT).show();
			}

			else
			{
				Log.d("Status:", "Photopicker canceled");
			}
		}
	}

	//Set the radius of the Blur. Supported range 0 < radius <= 25
	private static final float BLUR_RADIUS = 25f;

	public Bitmap blur(Bitmap image) {
		if (null == image) return null;

		Bitmap outputBitmap = Bitmap.createBitmap(image);
		final RenderScript renderScript = RenderScript.create(this);
		Allocation tmpIn = Allocation.createFromBitmap(renderScript, image);
		Allocation tmpOut = Allocation.createFromBitmap(renderScript, outputBitmap);

		//Intrinsic Gausian blur filter
		ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
		theIntrinsic.setRadius(BLUR_RADIUS);
		theIntrinsic.setInput(tmpIn);
		theIntrinsic.forEach(tmpOut);
		tmpOut.copyTo(outputBitmap);
		return outputBitmap;
	}

}