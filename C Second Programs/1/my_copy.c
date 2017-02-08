#include <stdio.h>
#include <stdlib.h>
#include <string.h>

main(){
    char source_file[80], target_file[80];
    char s[160];
    FILE *file, *target;
    size_t bytes_read;
    
    printf("Enter the first file space second file (ex: 'test1.txt test2,txt)'\n");
    scanf("%s%s",source_file, target_file);
    
    
    file = fopen(source_file, "r");
    target = fopen(target_file, "w");
    
    if(file == NULL) {
        fprintf(stderr, "File %s could not be opened\n", source_file);
        exit(1);
    }
    
    if(file == NULL) {
        fprintf(stderr, "File %s could not be opened\n", target_file);
        exit(1);
    }
	
    while(1){
	char buffer[1024];
	bytes_read = fread(buffer, strlen(buffer), 1, file);
	
	if (feof(file)){
		break;
	}
	
	fwrite(buffer, bytes_read, 1, target);
    }
   	
    printf("File copied successfully.\n");
    
    fclose(file);
    fclose(target);
    
    return 0;
}

