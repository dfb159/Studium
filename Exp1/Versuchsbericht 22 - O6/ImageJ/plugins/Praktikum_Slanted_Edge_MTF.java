/*************************************************************************
 *  Compilation:  javac SE_MTF_2xNyquist.java
 *  Execution:    java SE_MTF_2xNyquist
 *
 *  Calculates the Modulation Transfer Function (MTF) for an image of f rows and c columns of pixels selected. 
 *	For FFT requirements, uses a sample of power of 2 rows for both the calculation of the MTF and the SPP.
 *	
 *	Calcula la Funci�n de Transferencia de Modulaci�n (MTF) para una imagen de un tama�o f filas y 
 *	c columnas de pixels seleccionados. 
 *	Por requisitos de la fft, se utiliza una muestra de hileras potencia de 2 tanto para el calculo 
 *	de la MTF como de la SPP.
 *  
 *
 *  Limitations
 *  -----------
 *   * TIFF image only
 *  
 *  
 *************************************************************************/



import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.*;
import java.awt.event.*;
import ij.plugin.PlugIn;
import ij.plugin.frame.*;
import ij.measure.*;
import ij.io.*;
import java.math.BigDecimal;
import ij.text.*;
import java.util.*;
import java.lang.Object.*;
import java.lang.Integer;
import ij.process.ColorProcessor;
import ij.process.ImageConverter;
import ij.process.StackConverter;
import ij.plugin.filter.*;
import ij.plugin.filter.RGBStackSplitter;
import ij.*;
import ij.process.*;
import ij.gui.*;
import ij.measure.Calibration;
import java.awt.*;
import ij.plugin.FFT.*;
import java.util.EventObject;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;





public class Praktikum_Slanted_Edge_MTF implements PlugIn,WindowListener,DialogListener{

Frame frame;
ImagePlus imp,impOriginal;
ProfilePlot plotESF;
Plot plotResult;

int selectedWidth;
int selectedHeight;
int sppLength;



double[][] ESFArray;
double[][] LSFArray;
int[] edgePos;
double[] edgePosFit;

double[] Vector;
double[][] Array;
double[][] ArrayF;
double[][] ESFArrayF;
xy[] ESFArrayP;
double[] ESFVectorB;
double[][] LSFArrayF;

double[][] PosMax;
double[] PosMaxR;
double[]ESFVector;
double[]LSFVector;
double[]LSFDVector;
double[]LSFBVector;
double[]MTFVector;
double[]SPPVector;
double[]Max;
String title;
Roi roi;
Roi saveroi;

int imageWidth;
int optWidth;
int optHeight;
int sChannel;
int sFrequnits;
int type;
boolean isStack;
int roiType;
int sSize = 32;
int oSize = 1;
boolean cancel;
int bit;
int yMax;
double mmSensors=23.2; //Nikon D3200
int nPhotodetectors=6014;
double ny=1;


class xy implements Comparable<xy>{
	public double X;
	public double Y;
	
	public int compareTo(xy x) {
		if (x.X < this.X) {
			return 1;
		}
		else return -1;
	}
}

class RegressionResult {
	public double a;
	public double b;
}

// Message that asks the user what to do   
    public void run(String arg){	
	
		cancel=false;
		openImage();
		if (cancel==false){   //check if cancel during openImage()
			options();      
		}
		if (cancel==false){   //check if cancel during options()
				
				//Get Image data to create ESF
				ESFArray=getImageData(imp,roi);			
				
				//Diff to find the edges
				LSFArray=diffArray(ESFArray);
				
				//Find the edges
				edgePos=findMaxPosition(LSFArray);

				//Use linear regression to find the angle for the projection of the lines
				int[] rowNumbers = createVectorInt(0,edgePos.length-1,edgePos.length);
				RegressionResult regressionResult;
				regressionResult = linearRegression(rowNumbers,edgePos);
				edgePosFit=new double[edgePos.length];
				for(int i = 0; i< edgePosFit.length; i++){
					edgePosFit[i]=regressionResult.a+regressionResult.b*rowNumbers[i];
				}
				System.out.println("a: " + regressionResult.a+" b: "+regressionResult.b);
				
				
				
				
				
				
				//Draw edge on image with an overlay
				Rectangle r=roi.getBounds();				
				Overlay overlay = new Overlay(); 
				Roi roi1 = new Line(r.x+edgePos[0]+1,r.y,r.x+edgePos[edgePos.length-1]+1,r.y+r.height); 
				overlay.add(roi1); 
				imp.setOverlay(overlay); 
				
				double minPos;
				double maxPos;
				double minDist;
				
				if (edgePosFit[0]>edgePosFit[edgePosFit.length-1]){
					minPos = edgePosFit[edgePosFit.length-1];
					maxPos = edgePosFit[0];
				}
				else{
					maxPos = edgePosFit[edgePosFit.length-1];
					minPos = edgePosFit[0];
				}
				//Set max sample size
				
				if (r.width-maxPos<minPos){
					minDist=r.width-maxPos;
				}
				else {
					minDist=minPos;
				}
				
				if (sSize>minDist) {
					IJ.showMessage("Error","Edge to close to border!\n Process canceled");
					cancel=true;
				}
				
				if (cancel==false){
				
				boolean changedSize = false;
				while (2*sSize<minDist)
				{
					sSize = 2*sSize;
					changedSize = true;
					
				} 
				if (changedSize == true){
				IJ.showMessage("Information","Sample size increased to "+String.valueOf(sSize));
				}

					//Draw edge funtions for debugging				
					//createPlot(rowNumbers,edgePosFit,"Fit");
					//createPlot(rowNumbers,edgePos,"Max");
								
					ESFArrayP=projectArray(ESFArray,edgePosFit);
					
					//Draw projected data
					double[] Xarray = new double[selectedHeight*sSize];
					double[] Yarray = new double[selectedHeight*sSize];
					for (int k=0;k < selectedHeight*sSize;k++ ){
						Xarray[k]=ESFArrayP[k].X;
						//System.out.println("XP : " + ESFArrayP[k].X); 
						Yarray[k]=ESFArrayP[k].Y;
					}
					//createPlot(Xarray, Yarray,"Projected Array");
			
					//Bin data to create an vector with 2^n elements (n=oSize*sSize)
					ESFVectorB=binArray(ESFArrayP);
					
					//Plot binned Array
					rowNumbers = createVectorInt(0,ESFVectorB.length-1,ESFVectorB.length);
					//createPlot(rowNumbers,ESFVectorB,"ESF Binned Array");
									
					ESFArrayF=alignArray(ESFArray,edgePos);
					ESFVector=averageVector(ESFArrayF);
					generatePlot(ESFVectorB,"ESF");
										
						
					LSFArrayF=alignArray(LSFArray,edgePos);
					LSFVector=averageVector(LSFArrayF);
					//generatePlot(LSFVector,"LSF");
					
					LSFBVector=diff(ESFVectorB);
					generatePlot(LSFBVector,"LSF");	
					
					//LSFVector=LSFBVector;
						
					LSFDVector=FIRfilter(LSFVector);
					//generatePlot(LSFDVector,"LSFD");				
					MTFVector=fftConversion(LSFDVector, "MTF");
					//generatePlot(MTFVector,"MTF-A");
					

					LSFDVector=FIRfilter(LSFBVector);
					//generatePlot(LSFDVector,"LSF");				
					MTFVector=fftConversion(LSFDVector, "MTF");
					generatePlot(MTFVector,"MTF-B");
					System.out.println("Oversampling: " + String.valueOf(oSize)); 
					
					//Max=obtenerMax();
						//SPPVector=fftConversion(Max,"SPP");
						
						
						//generatePlot(MTFVector,"MTF");
						
						//generatePlot(SPPVector,"SPP");
					
				
			}}
			cleanImage();
		
    }	
	

	
	
	public void openImage(){
		
		boolean opButton=true;
		
		imp = WindowManager.getCurrentImage();
				
		if (imp==null){
			IJ.showMessage("Error","There are no images open\nProcess canceled");
			cancel=true;
		}
		
		
		if (cancel==false){
			title=imp.getTitle();
			type=imp.getType();
			switch(type){
				case ImagePlus.GRAY8:{  
					isStack=false;
					yMax=255;
				} break;
				case ImagePlus.GRAY16:{
					isStack=false;
					yMax=65535;
				} break;
				case ImagePlus.GRAY32:{
					isStack=false;
					yMax=2147483647;
				} break;
		    	case ImagePlus.COLOR_256:{
					isStack=true;
					yMax=255;
				} break;
				case ImagePlus.COLOR_RGB:{
					isStack=true;
					yMax=255;
				} break;
				default: yMax=255;
			}
		
 
			//Get the selection
			roi = imp.getRoi();	
			saveroi = roi;
			if (roi==null) {
				imp.setRoi(0, 0, imp.getWidth(), imp.getHeight());
				roi = imp.getRoi();
				opButton=IJ.showMessageWithCancel("Warning","All image selected");
				if (opButton==false) cancel=true;
        	}
			Rectangle r=roi.getBounds();
			selectedWidth=r.width;
			selectedHeight=r.height;
			imageWidth = imp.getWidth();
			
			//Rectangular selection
			roiType = roi.getType();
			if (!(roi.isLine() || roiType==Roi.RECTANGLE)) {
            	IJ.showMessage("Error","Line or rectangular selection required\nProcess canceled");
            	cancel=true;
        	}
		}
	}
	
	
	void cleanImage(){		
		if (imp!=null){
			imp.killRoi();
			imp.setRoi(saveroi);
		}	
			
	}

	void generateStack(){
		
		if (isStack==true && sChannel!=3){
			ImageConverter ic = new ImageConverter(imp);
            ic.convertToRGBStack();
			ImageStack is;
         	is=imp.getStack();
		}
	}
		
	void options(){
				
		GenericDialog gd = new GenericDialog("MTF - Options",frame);
				
		//User can choose the units
		gd.addDialogListener(this);
		String[]frequnits=new String[3];
		frequnits[0]="Absolute (lp/mm)";
		frequnits[1]="Relative (cycles/pixel)";
		frequnits[2]="Line widths per picture height (LW/PH)";
		gd.addChoice("Frequency units:",frequnits,frequnits[1]);
		
		
		
		//Input data
		nPhotodetectors=0;
		
		nPhotodetectors=imageWidth;
		
		
		gd.addMessage("Image width is "+String.valueOf(imageWidth)+" pixels. If the image has been croped, the numbers of photodetectors (pixels of the image sensor) might be different.");
		

		gd.addNumericField("Number of photodetectors: ",nPhotodetectors,0);
		gd.addNumericField("Sensor size (mm): ",mmSensors,1);
		
		((Component) gd.getNumericFields().get(0)).setEnabled(false);
		((Component) gd.getNumericFields().get(1)).setEnabled(false);

		gd.addMessage("ROI width is "+String.valueOf(selectedWidth)+" pixels. Sample size needs to be smaller.");
		gd.addMessage("Sample size will be automatically adjusted to maximum value.");
		//The user can choose the sample width
		String[]sampleSize=new String[5];
       		sampleSize[0]="32";
  	        sampleSize[1]="64";
  	        sampleSize[2]="128";
  	        sampleSize[3]="256";
			sampleSize[4]="512";
  	        gd.addChoice("Sample size (pixels):",sampleSize,sampleSize[0]);		
	
        gd.addMessage("ROI height is "+String.valueOf(selectedHeight)+" pixels. Oversampling size needs to be smaller.\n Depending on the image noise, an oversampling between 1 and 4 is recommended (more noise => smaller oversampling).\n Watch the LSF carefully!");
		
		String[]overSampling=new String[6];
       		overSampling[0]="1";
  	        overSampling[1]="2";
  	        overSampling[2]="4";
  	        overSampling[3]="8";
			overSampling[4]="16";
			overSampling[5]="32";
  	        gd.addChoice("Oversampling (pixels):",overSampling,overSampling[0]);					
			
		//If is a greyscale image there is no options avaliable
		if (isStack==false){
			gd.addMessage("This is a greyscale image, no options avaliable");
		}
		//If is a RGB image, user can choose each channel or the channels average to calculate MTF
		else{
			gd.addMessage("This is a three channel image, select an option");
			String[]channel=new String[4];
        	channel[0]="Red Channel";
        	channel[1]="Green Channel";
        	channel[2]="Blue Channel";
        	channel[3]="Channels average";
        	gd.addChoice("Channel",channel,channel[3]);
		}  		
		
		//Show General Dialog (MTF Options)
		gd.showDialog();
		
		//Ends the proccess
		if (gd.wasCanceled()){	
			cancel=true;
			cleanImage();
			return;
		}	 
		
	gd.addDialogListener(this);
	
    //Set the stat of the NumericText
	
    nPhotodetectors = (int)gd.getNextNumber();
	mmSensors = (double)gd.getNextNumber();
    sFrequnits=gd.getNextChoiceIndex();
	sSize=gd.getNextChoiceIndex();
	oSize=gd.getNextChoiceIndex();

		//Save options
      	
		String stringSize=sampleSize[sSize];
		sSize=Integer.parseInt(stringSize);
		System.out.println("Sample size: "+String.valueOf(sSize));
		
		
		stringSize=overSampling[oSize];
		oSize=Integer.parseInt(stringSize);
		System.out.println("Oversampling: "+String.valueOf(oSize));
			
			//Frequency units
		if(sFrequnits==0){
         	ny = (nPhotodetectors/mmSensors)*oSize;
			System.out.println("nPhotodetectors: "+String.valueOf(nPhotodetectors));
			System.out.println("mmSensors: "+String.valueOf(mmSensors));
		
		}
		if(sFrequnits==1){
         	ny = oSize;
		}
		if(sFrequnits==2){
         	ny = (nPhotodetectors*2)*oSize;
		}

		
		if (isStack==false){
			sChannel=0;
		}
		else{
			sChannel=gd.getNextChoiceIndex();
		}		
				
		if(isStack==true && sChannel!=3){
			generateStack();
			imp.setSlice(sChannel+1);
		}
	}
		
	public boolean dialogItemChanged(GenericDialog gd, AWTEvent e) {		
		sFrequnits=gd.getNextChoiceIndex();
		
		if(sFrequnits==1){
			((Component) gd.getNumericFields().get(0)).setEnabled(false);
			((Component) gd.getNumericFields().get(1)).setEnabled(false);
		}
		if(sFrequnits==0){
		     ((Component) gd.getNumericFields().get(0)).setEnabled(true);
		     ((Component) gd.getNumericFields().get(1)).setEnabled(true);
		}
		if(sFrequnits==2){
			((Component) gd.getNumericFields().get(1)).setEnabled(false);
		    ((Component) gd.getNumericFields().get(0)).setEnabled(true);
		}
		
    		
		return true;
		}
		
	
	//Create derivative for each line
	double[][] diffArray(double[][] Array){
		double[][] returnArray=new double[Array.length][Array[0].length];		
		for(int k=0;k<Array.length;k++){
			returnArray[k]=diff(Array[k]);
		}
		return returnArray;
	}
	

	//Gets the image data in the ROI
	double[][] getImageData(ImagePlus imp, Roi roi) {	
		
		Rectangle r=roi.getBounds();
		
		double[] line;
		double[][] resultArray;
		ProfilePlot profile;
		
		line=new double[r.width];
		
		resultArray=new double[r.height][r.width];
        for(int k=0;k<r.height;k++){
		    //select line 
			IJ.makeLine(r.x,k+r.y,r.x+r.width-1,k+r.y);
			//save values 
			profile = new ProfilePlot(imp);
			resultArray[k]=profile.getProfile();
		}
		return resultArray;

    }
	
	//Calculate the discrete derivative of a vector. Returns a vector of the same size.
	double [] diff(double[] Vector){
		double[] result=new double[Vector.length];		
		
		for(int k=0;k<Vector.length-1;k++){
			result[k]=Vector[k+1]-Vector[k];		
			if (result[k] < 0) {
				result[k]=(-1.0*result[k]);
			}
		}
		result[result.length-1]=result[result.length-2];
		return result;
	}
	
	
	int[] findMaxPosition(double[][] Array){
		
		int[] result = new int[Array.length];
		for(int k=0; k<Array.length;k++){
			result[k]=0;
			for(int i=0;i<Array[k].length;i++){
				if (Array[k][result[k]]<Array[k][i] || Array[k][result[k]]==Array[k][i]){
					result[k]=i;
				}
			}
		}
		return result;
	}
	
	
	
	//Calculate maximum value and find 32 positions to align
	void calculateMax(){
		
		PosMax=new double[selectedHeight][5] ;
		double[] vec;
		vec = new double[selectedHeight];
		boolean borderwarning = false;
		int posMax;
		int halfSize;
		halfSize=sSize/2;
		for(int k=0;k<selectedHeight;k++){
			posMax=0; 
			for(int i=0;i<selectedWidth;i++){
				if (LSFArray[k][posMax]<LSFArray[k][i] || LSFArray[k][posMax]==LSFArray[k][i]){
					posMax=i;
				}
			}
			vec[k]=posMax;
			
			//Max position needs to be at least halfSize pixel from border
			if (posMax<halfSize) {
				posMax = halfSize;
				borderwarning=true;
			
			}
			if (posMax>selectedWidth-halfSize) {
				posMax = selectedWidth-halfSize;
				borderwarning=true;
			}			
			
			//The maximum value position on the line
			PosMax[k][0]=posMax;
			//The maximum value on the line
			PosMax[k][1]=LSFArray[k][posMax];
			//Starting and ending position to align maximum values
			PosMax[k][2]=PosMax[k][0]-halfSize;
			PosMax[k][3]=PosMax[k][0]+halfSize;
		}
		if (borderwarning)
		{
			IJ.showMessage("Error","Max Position of LSF is too close to the border! Your results will be wrong! Move the ROI");
		}
		
		//double[] regressionResult = {0,0};
		//regressionResult = linearRegression(calculateXValues(vec,"PosMax"),vec);
		
		//generatePlot(vec,"MaxPos");
		
	}
	
	public double[][] alignArray(double[][] Array, int[] edgePosition){

		double[][] resultArray = new double[selectedHeight][sSize];
		int startpixel;
		int endpixel;
		double value;
		
		int len = Array.length;

		//Create new array aligned
		for(int k=0;k<selectedHeight;k++){
			
			//Initial and end positions of k-line
			startpixel=(int)edgePosition[k]-sSize/2;
			//System.out.println("Start: "+startpixel);
			//System.out.println("Ssize: "+sSize);
			for(int i=0;i<sSize;i++){	
				if (k<len){
					//System.out.println("len: "+Array[k].length);	
					if (((i+startpixel)<Array[k].length)  && ((i+startpixel)>-1))
					{
						double[] array1D= new double[Array[k].length];
						array1D = Array[k];
						value=array1D[i+startpixel];
									
						resultArray[k][i]=value;
					}
				}
		}
		}
		//final array - from 32 to 512 positions
		return resultArray;
	}
	
  	
	
	public double[] averageVector(double[][] Array){
		
		double result;
		int j;
		double[]Vector = new double[sSize];
		
		//Average of all linear ESF/LSF 	
		for(int i=0;i<sSize;i++){
			result=0;
			//Average of all rows i-position  
			for(int k=0;k<selectedHeight;k++){	
				result=result+Array[k][i];	
			}	
			result=result/(selectedHeight);
			Vector[i]=result;
		}
		return Vector;
	}
	
	
    public xy[] projectArray(double[][] Array,double[] edgePosition) {
		xy[] ArrayP = new xy[Array.length*(sSize+4)];
		
		int startpixel;
		int endpixel;
		
		int index = 0;
		//Create new array aligned
		for(int k=0;k<Array.length;k++){
			
			//Initial and end positions of k-line
			startpixel=(int)(edgePosition[k]-sSize/2-1);
			endpixel=(int)(startpixel+sSize+4);
			if (startpixel < 0) {startpixel=0;}
			if (endpixel > Array[k].length) {endpixel = Array[k].length;}
			for(int i=startpixel;i<endpixel;i++){	
					ArrayP[index] = new xy();
					ArrayP[index].Y=Array[k][i];
					ArrayP[index].X=i-edgePosition[k];
					//System.out.println("X: "+ArrayP[index].X);
					index++;
		}
		}
		Arrays.sort(ArrayP); 
		return ArrayP;	
	}
	

	
	public double[] binArray(xy[] xyArray) {
		double[] result = new double[sSize*oSize];
		int count = 0;
		int N = 0;
		
		//System.out.println("Oversampling: "+String.valueOf(oSize));
		for (int i = 0; i< sSize*oSize; i++){
			N = 0;
			result[i] = 0;
			//System.out.println("Bin number: "+String.valueOf(i));
			while ((xyArray[count].X+sSize/2)<i/1.0/oSize)
			{
				
				if (count<xyArray.length) {
					result[i]+=xyArray[count].Y;
					N++;
				}
				count++;
				if (count==xyArray.length) { 
					System.out.println("Break! At bin number "+String.valueOf(i));
					break;
					
				}
				
			}
			if (N>0) {
				result[i]=result[i]/N;
			}
			if (count==xyArray.length) { 
				System.out.println("Break! "+String.valueOf(i));
				break;
				
				}
		}	
	   
		return result;
	}
	
	double[] FIRfilter(double[] Vector){
		
	
		double[] result = new double[Vector.length*2];
		int j = 0;
		int i = 0;
		for(i=0;i<(result.length-3); i++){
			if(i % 2 == 0) { 
				result[i]= Vector[j];
				j=j+1;
			}
			else {
				result[i]= ((0.375*Vector[(j-1)]) + (0.75*Vector[(j)]) - (0.125*Vector[(j+1)]));
			}
		}  
					
		result[i] = ((Vector[j-1] + Vector[j])*0.5);
		result[i+1] = Vector[j];
		result[i+2] = Vector[j];
			
		int indexMax = 0;
		double valorMax = result[0];
		for(i=0;i<result.length;i++){
 			if(valorMax < result[i]){
				indexMax = i;
				valorMax = result[i];
			}
		}
		i=indexMax;
		result[i-1]=((result[i-2] + result[i])*0.5);
		
		return result;
	}

	
	public int longPotDos(int length){
		int N;
		double log = Math.log(length)/Math.log(2);
		N=(int)Math.pow(2,(int)Math.floor(log));
		return N;
	}
	
	
	public double[] obtenerMax(){
		int N=longPotDos(selectedHeight);
		Max=new double[N];
		for(int k =0;k<N;k++){
			Max[k]=PosMax[k][1];
		}
		return Max;
	}

	
	void createPlot(double[] xValues, double[] yValues, String title){
		plotResult = new Plot(title, "", "", xValues, yValues);
		//plotResult.setLimits(minValue(xValues),Vector.length,0,yMax);
		plotResult.draw();
		plotResult.show();
	}
	
	void createPlot(int[] xValues, double[] yValues, String title){
		
		double[] xValuesD = new double[xValues.length];
		for (int i = 0; i<xValues.length; i++){
			xValuesD[i]=xValues[i];
		}
	    plotResult = new Plot(title, "", "", xValuesD, yValues);
		//plotResult.setLimits(minValue(xValues),Vector.length,0,yMax);
		plotResult.draw();
		plotResult.show();
	}
	
	void createPlot(int[] xValues, int[] yValues, String title){
		double[] xValuesD = new double[xValues.length];
		for (int i = 0; i<xValues.length; i++){
			xValuesD[i]=xValues[i];
		}
		double[] yValuesD = new double[yValues.length];
		for (int i = 0; i<yValues.length; i++){
			yValuesD[i]=yValues[i];
		}
		
		plotResult = new Plot(title, "", "", xValuesD, yValuesD);
		//plotResult.setLimits(minValue(xValues),Vector.length,0,yMax);
		plotResult.draw();
		plotResult.show();
	}
	
		
		
	
	void generatePlot(double[] Vector, String plot){
		
		double[]xValues;
		String ejeX="pixel";
		String ejeY="";
		String allTitle="";
		ImageProcessor imgProc;
		
		
		//If MTF plot, calculate the scale of cycles per pixel for x-axis values
		
		
		//plot titles 	

		allTitle=plot + "_" + title;	
		
		if (plot=="ESF"){
			ejeY="Grey Value";
		}

		if (plot=="LSF"){ 
			ejeY="Grey Value / pixel";
		}
		
		if (plot=="LSFD"){ 
			ejeY="Grey Value / pixel";
		}
		
		if (plot=="MTF" || plot=="MTF-A" || plot=="MTF-B"){
			ejeY="Modulation Factor";
			plot="MTF";
			//Units
			if(sFrequnits==0){
			ejeX ="lp/mm";
			ny = (nPhotodetectors/mmSensors)*oSize;
			}
			if(sFrequnits==1){
			ejeX ="Cycles/Pixel";
			ny = oSize;
			}
			if(sFrequnits==2){
			ejeX ="Line Width/Picture Height";
			ny = (nPhotodetectors*2)*oSize;
			}

		}
		
		if (plot=="MaxPos"){
			ejeY="Max Position";
			ejeX="Line";			
		}
		
		if (plot=="SPP"){
			ejeY="SPP";
		}
		xValues=calculateXValues(Vector,plot);
				
		plotResult = new Plot(allTitle, ejeX, ejeY, xValues, Vector);
		//plotResult.setLimits(1,Vector.length,0,yMax);
		//plot limits
		if (plot=="ESF"){
			plotResult.setLimits(1,Vector.length,0,yMax);
			for (int i=0;i< selectedHeight;i++){
			Vector=ESFArrayF[i];
			}
		}
		
		if (plot=="LSF"){
			plotResult.setLimits(1,Vector.length,0,maxValue(Vector));
			for (int i=0;i< selectedHeight;i++){
			Vector=LSFArrayF[i];
			}
		}
		
		if (plot=="MTF" || plot=="MTF-A" || plot=="MTF-B"){
			
			
			plotResult.setLimits(0,ny/oSize,0,1);
			
		}
				
		if (plot=="SPP"){
			plotResult.setLimits(1,Vector.length,0,1);
		}
		
		if (plot=="MaxPos"){
			
			
			//double[] regressionResult = {0,0};
			//regressionResult = linearRegression(xValues,Vector);
			//for (int i = 0; i<Vector.length;i++)
			//{Vector[i]=regressionResult[0]+regressionResult[1]*xValues[i];}
			//plotResult.setLimits(1,Vector.length,minValue(Vector),maxValue(Vector));
			//plotResult.addPoints(xValues,Vector,Plot.LINE);
			
		}
		 
		plotResult.draw();
		plotResult.show();
	}
	
	
	

	public double[] calculateXValues(double[] Vector, String plot){
		
		int N=Vector.length;
		double[]xValues = new double[N];
		
		if(plot=="MTF"){
			xValues[0]=0;
			//Scale of values for x-axis
			for(int i=1;i<N;i++){
				//xValues[i]=xValues[i-1]+(0.5/(N-1));
				xValues[i]=xValues[i-1]+(ny/(N-1));
			}
		}else{
			for(int i=0;i<N;i++){ 
				xValues[i]=i+1;
			}
		}
		return xValues;
	}
	
public double maxValue(double[] vector) {
	double max = vector[0];
	for (int k = 0; k < vector.length; k++) {
		if (vector[k] > max) {
			max = vector[k];
		}
	}
	return max;
}

public double minValue(double[] vector) {
	double min = vector[0];
	for (int k = 0; k < vector.length; k++) {
		if (vector[k] < min) {
			min = vector[k];
		}
	}
	return min;
}
	
	double[] createVector(double min, double max, int length) {
		
		double[] result = new double[length] ;
		for(int i = 0; i<length; i++){
			result[i]=min+i*(max-min)/(length-1);
		}
		return result;
	}
	
	
	int[] createVectorInt(int min, int max, int length) {
		
		int[] result = new int[length] ;
		for(int i = 0; i<length; i++){
			result[i]=min+i*(max-min)/(length-1);
		}
		return result;
		
	}	
	
	//data type conversion from complex to double, to implement fft
	public double[] fftConversion(double[] Vector, String plot){
        
		//Only half are necessary
		int N=Vector.length;
		int M=Vector.length/2;
		double divisor;
		Complex[] ArrayComplex = new Complex[N];
		Complex[] VectorFFTC = new Complex[N];
		double[]VectorFFTD=new double[M];
				
						
        for (int i = 0; i < N; i++){ 
			//A double array is converted into a complex array ; imaginary part=0           
            ArrayComplex[i] = new Complex(Vector[i], 0); 
        }
		//FFT operation
        VectorFFTC = fft(ArrayComplex); 
		
		
		if(plot=="SPP"){
			//Reject the first one
			for (int i = 1; i < M; i++){
				//absolute value (module)
				VectorFFTD[i-1]=VectorFFTC[i].abs()/VectorFFTC[1].abs();
			}
		}else{
			for (int i = 0; i < M; i++){
				//absolute value (module)
            	VectorFFTD[i]=VectorFFTC[i].abs()/VectorFFTC[0].abs(); 
			}
        }
		
		//Normalize
		if(plot=="SPP"){
			divisor=findMaxSPP(VectorFFTD);
		}else{			
			divisor=VectorFFTD[0];	
		}
		
		for (int i = 0; i < M; i++){
           	VectorFFTD[i]=VectorFFTD[i]/divisor; 
        }
		return VectorFFTD;
    }
	
	
	public double findMaxSPP(double[] Vector){
		double max = 0;
        double value;
        for (int i=0; i<Vector.length; i++) {
            if (Vector[i]>max) max=Vector[i];
        }
		
		return max;
	}
	

	public class Complex {
    	private final double re;   //the real part
    	private final double im;   //the imaginary part

    	// create a new object with the given real and imaginary parts
    	public Complex(double real, double imag) {
        	this.re = real;
        	this.im = imag;
    	}
		
		// return a string representation of the invoking object
	    public String toString()  {return re + " + " + im + "i"; }
		
		// return a new object whose value is (this + b)
    	public Complex plus(Complex b) { 
        	Complex a = this;             // invoking object
        	double real = a.re + b.re;
        	double imag = a.im + b.im;
        	Complex sum = new Complex(real, imag);
        	return sum;
    	}
		
    	public Complex minus(Complex b) { 
        	Complex a = this;   
        	double real = a.re - b.re;
        	double imag = a.im - b.im;
        	Complex diff = new Complex(real, imag);
        	return diff;
    	}		
		
		// return a new object whose value is (this * b)
    	public Complex times(Complex b) {
        	Complex a = this;
        	double real = a.re * b.re - a.im * b.im;
        	double imag = a.re * b.im + a.im * b.re;
        	Complex prod = new Complex(real, imag);
        	return prod;
    	}
		
		// return |this|
    	public double abs() { return Math.sqrt(re*re + im*im);  }
	}	
	
	
		public Complex[] fft(Complex[] x) {
        
        	int N = x.length;
        	Complex[] y = new Complex[N];

	        // base case
    	    if (N == 1) {
        	    y[0] = x[0];
            	return y;
        	}

        	// radix 2 Cooley-Tukey FFT
        	if (N % 2 != 0) throw new RuntimeException("N is not a power of 2");
        	Complex[] even = new Complex[N/2];
        	Complex[] odd  = new Complex[N/2];
        	for (int k = 0; k < N/2; k++) even[k] = x[2*k];
        	for (int k = 0; k < N/2; k++) odd[k]  = x[2*k + 1];

	        Complex[] q = fft(even);
        	Complex[] r = fft(odd);

        	for (int k = 0; k < N/2; k++) {
            	double kth = -2 * k * Math.PI / N;
            	Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
            	y[k]       = q[k].plus(wk.times(r[k]));
            	y[k + N/2] = q[k].minus(wk.times(r[k]));
        	}
        	return y;
    	}

	public RegressionResult linearRegression (int[] xValues, int[] yValues) {
		long sumX = 0;
		long sumX2 = 0;
		long sumXY = 0;
		long sumY = 0;
		long D = 0;
		
		double a = 0;
		double b = 0;
		
		int N = xValues.length;
		if (yValues.length < N) { N=yValues.length;}
		
		for (int i=0; i<N;	i++) {
			sumX+=xValues[i];
			sumX2+=xValues[i]*xValues[i];
			sumXY+=xValues[i]*yValues[i];
			sumY+=yValues[i];
			//System.out.println("X,Y: " + xValues[i] + ", "+ yValues[i]);
		}
		//System.out.println("SX, SX2, SY, SXY: " + sumX + ", "+ sumX2+ ", "+ sumY+ ", "+ sumXY);
		D=N*sumX2-sumX*sumX;
		//System.out.println("D: " + D);
		a=1.0*(sumX2*sumY-sumX*sumXY)/D;
		b=1.0*(N*sumXY-sumX*sumY)/D;
		//IJ.showMessage("Linear Regression","a: " + String.valueOf(a) + " b: "+String.valueOf(b));
		RegressionResult result = new RegressionResult();
		result.a=a;
		result.b=b;
		return result;
	}		
	
		
		
	public void windowOpened(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
    public void windowClosing(WindowEvent e){
		frame.dispose();
		}
    public void windowClosed(WindowEvent e){}

}


