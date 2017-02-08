#include "ArrayList.h"
#include "ArrayList.cpp"
#include <iostream>
using namespace std;

/*
 * @author Eric Haasch
 * SEVERAL MODIFICATIONS WERE DONE TO GET THIS FILE TO WORK.
 * LINE 1-2 #INCLUDE "ARRAYLIST.CPP" ADDED
 * LINE 14  ArrayList<int> arr; wasArrayList arr;
 */
int main()
{
    ArrayList<int> arr;

	for (int i=1;i<=50;i++)
	{
		arr.push_back(i);
	}

	cout << "Should contain numbers 1..50, is ";

	cout << arr.toString() << endl;

	for (int i=arr.size()-1;i>=1;i--)
	{
		arr.erase(arr[i]);
	}

	cout << "Should contain only 1, is ";
	cout << arr.toString() << endl;

    arr.erase(arr[0]);
	for (int i=2;i<=50;i++)
	{
		if (i<=2)
			arr.push_back(i);
		else
		{
			int j=0;
			while ((j<arr.size()) && (i%arr[j]!=0))
				j++;

			if (j==arr.size())
			{
				arr.push_back(i);
			}
		}
	}

	cout << "Prime numbers between 1 and 50 are: "<< arr.toString() << endl;
}
