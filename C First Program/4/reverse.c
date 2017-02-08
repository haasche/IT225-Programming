#include <stdio.h>
#include <stdlib.h>

main(){
	int array[10];
	FILE *file;
	char *filename = "./ehaasch_foo.txt";
	puts("Enter 10 integers: ");
	
	file = fopen(filename, "w");
	if (file == NULL) {
		printf(stderr, "File %s could not be opened\n", filename);
		exit(1);
	}

	int i;
	for (i=0;i<10;i++){
		scanf("%d", &array[i]);
		//scanf = ascii code to integer data type
	}

	for (i=9;i>=0;i--){
		fprintf(file,"%d", array[i]);
		fprintf(file, "%s", " ");

		//debug purposes, uncomment to debug
		//printf("%d", array[i]);
		//printf("%s", " ");
	}
	
	fclose(file);
	return 0;
}
