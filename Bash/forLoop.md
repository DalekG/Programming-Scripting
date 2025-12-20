# For Loop
A for loop allows a set of commands to be repeatedly executed fpr each item in a list of words, an array, a range of numbers, of a C-style expression.

#### For Loop Build
for {variable} in {list}; do (command); done

#### Catting contents of files
**current directory holds file1, file2, file3, and file4**
for l in $(ls); do cat $l; done
    - hi
    - there.
    - how are
    - you?