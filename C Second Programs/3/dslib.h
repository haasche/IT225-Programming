/*@author ehaasch
 *@date 2/6/16
 *This file is the header file of the stack implementation
*/

typedef struct stack {
	int size;
	int elements;
	int *stack_ptr;
} stack;

void stack_init(stack *s, int capacity);
int stack_size(stack *s);
int stack_pop(stack *s);
void stack_push(stack *s, int e);
void stack_deallocate(stack *s);
