# Document Search

This project will search the documents in the folder of the project and search for a given term (word, phrase, or regex expression) depending on the option you first choose.  It will print out the files in the folder depending on the relevency of the term.  Relevency depending on the occurances of the phrase.
It will also show you how much time is elapsed from each method of searching.

```
Enter the search term: 'user enters search term'
Search Method: 1) String Match 2) Regular Expression 3) Indexed
Search results:
File2.txt – X matches
File1.txt - X matches
File3.txt – X matches

Elapsed time: 40 ms
```

## Assumptions

If the document says "I would love to work at the company Target, not any other retailer"

if the search term is "the"

for search method 1) String Match: it will return 1

for search method 2) Regular Expression: 2 ('the', 'other')

for search method 3) Indexed: 1


## Notes

I haven't added the unit tests in here. I have tested with large data sets and will have the unit tests by the meeting.  I noticed that the index search is more efficient with larger data sets.
I tried to make the code as non-repetitive as possible (the the read() method). I'm sure with more time it could've been more efficient.  However with the small data sets so far it all works.

## Scaling

If there are 5000 requests/second, it would probably be better to create an index and then do look ups thus there is O(1) look up time.  However during my research on java libraries to help make this more efficient there is Lucene and other already created libraries.  On a service level there might be more efficient languages to code this in. Or perhaps a different way to structure the service all together.  With more time, I know there's many ways to make this more efficient.

## Built With

* Eclipse Oxygen

