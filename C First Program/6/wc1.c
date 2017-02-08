#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <unistd.h>

main(){

	int wordCount = 0;
	int lineCount = 0; 
	
	FILE *file;
	char fileName[80];
	const char s[2] = " ";
	char *token;
	

	printf("Please enter the file name: ");
	scanf("%s",  fileName);
	
	file = fopen(fileName, "r");
	if (file == NULL) {
		fprintf(stderr, "File %s could not be opened\n", fileName);
		exit(1);
	}
	
	while(1){
		char buffer[80];
		fgets(buffer, 80, file);
		

		if (feof(file))
			break;
		++lineCount;

		token = strtok(buffer, s);
		while(token != NULL){
			++wordCount;
			token = strtok(NULL, s);
		}
		
	}

	printf("wordcount %d \n", wordCount);
	printf("linecount %d \n", lineCount);
	
}




