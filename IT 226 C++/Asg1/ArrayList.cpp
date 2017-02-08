/**
 * @author Eric Haasch
 * @date 11/1/16
 */

#include <iostream>
#include <sstream>
#include "ArrayList.h"
using namespace std;

template<typename T>
ArrayList<T>::ArrayList() {
    capacity = 10;
    array = new T[capacity];
    sizeArray = 0;
    for(int i=0; i<capacity; i++) {
        array[i] = 0;
    }
}

template <typename T>
string ArrayList<T>::toString() {
    stringstream x;
    x << "[";
    for(int i = 0; i < capacity + 1; i++) {
        x << array[i];
        if(i < capacity) {
            x << ',';
            x << ' ';
        }
        else {
            x << ']';
            x << '.';
        }
    }
    return x.str();
}

template<typename T>
T ArrayList<T>::size() {
    return sizeArray;
}

template <typename T>
T ArrayList<T>::push_back(T element) {
    if(sizeArray > capacity) {
        capacity++;
        array[sizeArray] = element;
        sizeArray++;
    }
    else {
        array[sizeArray] = element;
        sizeArray++;
    }
}

template <typename T>
T ArrayList<T>::erase(T element) {
    for(int i = 0; i< capacity + 1; i++) {
        if(element == array[i]) {
            capacity--;
            for(int j = i; j < capacity + 1; j++) {
                array[j] = array[j + 1];
            }
        }
    }
    sizeArray--;
}
