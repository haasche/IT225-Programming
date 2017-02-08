/*@author ehaasch
 *@date 2/6/16
 *This file is the driver file of the stack implementation
*/

#include <stdio.h>
#include <stdlib.h>
#include "dslib.h"

int main() {

	struct stack my_stack;
	int a;
	int b;

	stack_init(&my_stack, 2); //makes the stack

	printf("Elements in stack: %i\n",stack_size(&my_stack)); //should return the number 		of elements in the stack
	
	printf("Please enter an integer to add to the stack: ");
	scanf("%d", &a);
	
	stack_push(&my_stack, a); //adds number entered from terminal

	printf("Size of stack should be 1: %i\n",stack_size(&my_stack));
	printf("Popping out what is on top: %i\n",stack_pop(&my_stack));

	printf("Please enter two integers to add to the stack: ");
	scanf("%d%d", &a, &b);
	stack_push(&my_stack, a); // more push/pop test
	stack_push(&my_stack, b);
	printf("Size of stack should be 2: %i\n",stack_size(&my_stack));
	printf("Popping out what is on top: %i\n",stack_pop(&my_stack));

	printf("Size of stack should be 1: %i\n",stack_size(&my_stack));
	printf("Popping out what is on top: %i\n",stack_pop(&my_stack));

	printf("Size of stack should be 0: %i\n",stack_size(&my_stack));
	
	if(stack_size(&my_stack) == 0){
		printf("\n************\nThis test was successful!\n************\n");
	}
	else{
		printf("\n************\nTest failure.\n************\n");
	}

	stack_deallocate(&my_stack); // deallocate memory given to the stack

	return 0;
}
