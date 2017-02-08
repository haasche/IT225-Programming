/*
 * @author Eric Haasch
 * @date 10/11/16
 */

#include <iostream>
#include <fstream>
#include "FileAssignment.h"
using namespace std;

FileReader::FileReader(){
}

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


  void FileReader::topReader() {
    oFile.open("offer.txt");

    cout << "Enter the Date: " << endl;
    getline(cin, date);

    cout << "Enter the Full Name: " << endl;
    getline(cin, name);

    cout << "Enter the Address: " << endl;
    getline(cin, address);

    cout << "Enter Job Title: " << endl;
    getline(cin, jobTitle);

    ifstream file("toAddress.txt");
    int length = dateReplace.length() + 5;
    int length2 = nameReplace.length() + 5;
    int length3 = address.length() + 10;
    int length4 = jobTitle.length() + 10;

    while (getline(file, oRead)) {
      while (true) {
    	size_t pos = oRead.find(dateReplace);
        if (pos != string::npos) {
          oRead.replace(pos, length, date);
        } else {
          break;
        }
      }
      while (true) {
    	size_t pos = oRead.find(nameReplace);
        if (pos != string::npos) {
          oRead.replace(pos, length2, name);
        } else {
          break;
        }
      }
      while (true) {
    	size_t pos = oRead.find(addressReplace);
        if (pos != string::npos) {
          oRead.replace(pos, length3, address);
        } else {
          break;
        }
      }
      while (true) {
    	size_t pos = oRead.find(jobReplace);
        if (pos != string::npos) {
          oRead.replace(pos, length4, jobTitle);
        } else {
          break;
        }
      }
      oFile << oRead << endl;
    }
    oFile << "" << endl;
    oFile.close();
  }

  void FileReader::midReader() {
    bool yes = true;
    string answer = "";
    iFile.open("offer.txt", fstream::in | fstream::out | fstream::app);

    cout << "Enter the Company Name: " << endl;
    getline(cin, companyName);

    cout << "Enter List of Duties: " << endl;
    getline(cin, duty);

    ifstream file("body.txt");
    int len = companyReplace.length() + 5;
    int len1 = jobReplace.length() + 5;
    int len2 = dutiesReplace.length() + 5;

    while (getline(file, oRead)) {
      while (true) {
    	size_t pos = oRead.find(companyReplace);
        if (pos != string::npos) {
          oRead.replace(pos, len, companyName);
        } else {
          break;
        }
      }
      while (true) {
    	size_t pos = oRead.find(jobReplace);
        if (pos != string::npos) {
          oRead.replace(pos, len1, jobTitle);
        } else {
          break;
        }
      }
      while (yes == true) {
    	size_t pos = oRead.find(dutiesReplace);
        if (pos != string::npos) {
          oRead.replace(pos, len2, "\n      1: " + duty);
        } else {
          break;
        }
      }
      iFile << oRead << endl;
    }
    iFile << "" << endl;
    iFile.close();
  }

  void FileReader::botReader() {
    iFile.open("offer.txt", fstream::in | fstream::out | fstream::app);

    cout << "Enter Managers Name: " << endl;
    getline(cin, manager);

    cout << "Enter Managers Position: " << endl;
    getline(cin, managerPosition);

    ifstream file("footer.txt");
    int len = managerReplace.length() + 10;
    int len1 = positionReplace.length() + 10;
    int len2 = companyReplace.length() + 10;
    int len3 = nameReplace.length() + 5;
    int len4 = dateReplace.length() + 5;

    while (getline(file, oRead)) {
      while (true) {
    	size_t pos = oRead.find(managerReplace);
        if (pos != string::npos) {
          oRead.replace(pos, len, manager);
        } else {
          break;
        }
      }
      while (true) {
    	size_t pos = oRead.find(positionReplace);
        if (pos != string::npos) {
          oRead.replace(pos, len1, managerPosition);
        } else {
          break;
        }
      }
      while (true) {
    	size_t pos = oRead.find(companyReplace);
        if (pos != string::npos) {
          oRead.replace(pos, len2, companyName);
        } else {
          break;
        }
      }
      while (true) {
        int pos = oRead.find(nameReplace);
        if (pos != string::npos) {
          oRead.replace(pos, len3, name);
        } else {
          break;
        }
      }
      while (true) {
    	size_t pos = oRead.find(dateReplace);
        if (pos != string::npos) {
          oRead.replace(pos, len4, date);

        } else {
          break;
        }
      }
      iFile << oRead << endl;
    }
    iFile.close();

};

int main() {
  FileReader fr;
  fr.topReader();
  fr.midReader();
  fr.botReader();
  return 0;
}
