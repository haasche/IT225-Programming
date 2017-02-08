#include <stdio.h>
#include <stdlib.h>

main(){
	FILE *file;
	char *filename = "./ehaasch_foo";
	/* open the file for writing */
	file = fopen(filename, "w");
	if (file == NULL) {
		fprintf(stderr, "File %s could not be opened\n", filename);
		exit(1);
		}
	/* write to the file */
	fprintf(file, "Hello World!\n");
	/* close the fi */
	fclose(file);
}
