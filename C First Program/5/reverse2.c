#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <unistd.h>

main(){

	int array[10];
	int file;
	char *filename = "./ehaasch_foo.txt";
	char buffer[20];
	
	printf("Enter 10 integers: \n");
	file = open(filename,  O_WRONLY | O_CREAT, 0644);
	if (file == -1) {
		fprintf(stderr, "File %s could not be opened\n", filename);
		exit(1);
	}

	int i;
	for (i=0;i<10;i++){
		scanf("%d", &array[i]);
	}

	for (i=9;i>=0;i--){
		printf("%d ", array[i]);
		sprintf(buffer, "%d ", array[i]);
		write(file, buffer, strlen(buffer));
		
	}
	
	close(file);
	return 0;
}
