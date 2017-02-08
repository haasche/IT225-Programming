#include <stdio.h>
#include <stdlib.h>

main(){
	FILE *file;
	char *filename = "./ehaasch_foo";
	/* open the file for writing */
	file = fopen(filename, "r");
	if (file == NULL) {
		fprintf(stderr, "File %s could not be opened\n", filename);
		exit(1);
	}
	/* loop while reading a line at a time from the file and printing */
	while (1) {
	char buffer[80];
	fgets(buffer, 80, file);
	/* if it’s the end of file, break out of this loop */
	if (feof(file))
		break;
	printf("%s", buffer);
	}
	/* close the file */
	fclose(file);
}
