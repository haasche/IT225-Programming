/**
 * @author Eric Haasch
 * @date 11/1/16
 */

#ifndef ArrayList_H
#define ArrayList_H
#include<iostream>
using namespace std;

template<typename T>
class ArrayList {
private:
    T capacity;
    T *array;
    T sizeArray;
public:
    ArrayList();
    string toString();
    T size();
    T push_back(T element);
    T erase(T element);
    int& operator[](const int index) {
        return array[index];
    }
};

template <typename string>
string toString();

template<typename T>
T size();

template <typename T>
T push_back(T element);

template <typename T>
T erase(T element);

#endif
