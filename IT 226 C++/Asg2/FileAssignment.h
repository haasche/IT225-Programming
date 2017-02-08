/*
 * @author Eric Haasch
 * @date 10/11/16
 */

#ifndef FILEASSIGNMENT_H
#define FILEASSIGNMENT_H

using namespace std;

class FileReader{
public:
	  FileReader();
	  fstream iFile;
	  ofstream oFile;

	  string duty;
	  string oRead;
	  string date;
	  string name;
	  string address;
	  string jobTitle;
	  string companyName;
	  string manager;
	  string managerPosition;

	  string dateReplace = "_DATE";
	  string nameReplace = "_NAME";
	  string addressReplace = "_ADDRESS";
	  string jobReplace = "_JOBTITLE";
	  string companyReplace = "_COMPANYNAME";
	  string dutiesReplace = "_DUTIES";
	  string managerReplace = "_MANAGERNAME";
	  string positionReplace = "_MANAGERPOSITION";

	  void topReader();
	  void midReader();
	  void botReader();
};
#endif
