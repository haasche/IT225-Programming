/*@author ehaasch
 *@date 2/6/16
 *This file is the c file of the stack implementation
*/

#include "dslib.h"
#include <stdlib.h>

void stack_init(stack *s, int capacity) {
	s->stack_ptr = malloc(capacity*sizeof(int));
	s->size = capacity;
	s->elements = 0;  
}

int stack_size(stack *s) {
	return s->elements;
}

int stack_pop(stack *s) {
	if (s->elements == 0) {
		return 0;	
	}
	s->elements--;
	return s->stack_ptr[s->elements+1];
}

void stack_push(stack *s, int e) {
	if (s->elements+1 > s->size) {
	}
	else {
		s->stack_ptr[s->elements+1] = e;
		s->elements++;
	}
}

void stack_deallocate(stack *s) {
	free(s->stack_ptr);
}
