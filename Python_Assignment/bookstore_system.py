#Importing libraries needed for graph
import matplotlib.pyplot as plt

#Creating empty lists to fill with information from the file
allbooks=[] #This is the master list, containing all information on the books
#This list is used in the bar chart for labels and the add book function to check if the genre exists
genre_list=['religion','biography','science','fiction']
#Opening a file and saving the contents to the empty lists
for book in open('books.txt'):
    items = book.rstrip('\n').split(',')   # removing the new line characters and splitting string to seperate entities
    items = [item.strip() for item in items]  # removing extra white space
    if book.startswith('#') == False:  #If the line starts with '#', then ignore it
        allbooks.append(items) #addinig the books to the allbooks list
    else:
        pass

###TASK 3: Report detailing number of books in each genre
#Creating a dictionary which says the total titles in each genre
#This was made into a function as it needed to update with any new books
def genre_dic():
    genre_dict={'religion':0,'biography':0,'science':0,'fiction':0}
    for book in allbooks:
    #for every book in the genre, add the number in stock to the total
        if book[-1]=='religion':
            genre_dict['religion']+=int(book[-2])
        elif book[-1]=='biography':
            genre_dict['biography']+=int(book[-2])
        elif book[-1]=='science':
            genre_dict['science']+=int(book[-2])
        elif book[-1]=='fiction':
            genre_dict['fiction']+=int(book[-2])
        else:
            pass
    return genre_dict

#Calculating the number of titles and copies of each book in stock
def total_values():
    value_dic={'titles':0,'copies':0}
    for book in allbooks:
        value_dic['copies']+=int(book[-2])
        value_dic['titles']+=1
    return value_dic

####Task 1
#Function to calculate the total value of the titles
def price():
    total=0
    for book in allbooks:
        #Multiplying the price by the stock and rounding it to 2 decimal places
        total+=round(float(book[-3]) * float(book[-2]),2)
    return total

#TASK 2: calculate avg price of books
#Calculating the average price of a book in stock
def avg_price():
    total=0
    #Saving the dictionary returned from total_values function to a variable
    value_dict=total_values()
    for book in allbooks:
        #if the book is in stock, include it in the calculation
        if int(book[-2])>0:
            total+=float(book[-3])
            #Dividing the total value of the books by the number of titles
            avg=round(total/value_dict['titles'],2)
        else:
            pass
    return avg

#######TASK 5: Query if a book is available, present option of adding or removing stock
def stock():
    loop=True
    #starting a loop which checks if the title exists in the master book list
    while loop==True:
        book_sel=input("Enter a book's title: ")
        for book in allbooks:
            if book_sel==book[1]:
                loop=False
                break
        else:
            print("That book isn't on the system. Try again.")
    #If the book exists on the system, more options are presented to the user
    for book in allbooks:
        if book_sel==book[1]:
            print('[Current Stock: '+book[-2]+']')
            while True:
                print('[1]Increase Stock [2]Decrease Stock [3]Back')
                input_stock=input('Select: ')
                try:
                    if input_stock == '1':
                        input_stock_level=int(input("Enter the number of books to add: "))
                        #Adding the user specified value to the current stock
                        new_stock=int(book[-2])+input_stock_level
                        old_stock=book[-2]
                        #Inserting the new value to the list
                        book.insert(-2,str(new_stock))
                        #Removing the old value
                        book.remove(old_stock)
                        print(*book, sep = ", ")
                    elif input_stock == '2':
                        input_stock_level=int(input("Enter the number of books to remove: "))
                        #Removing the user specified value from the current stock
                        new_stock=int(book[-2])-input_stock_level
                        old_stock=book[-2]
                        #Checking if the book is still in stock
                        if new_stock<=0:
                            print('[OUT OF STOCK]')
                            book.insert(-2,'0')
                            book.remove(old_stock)
                        else:
                            book.insert(-2,new_stock)
                            book.remove(old_stock)
                        print(*book, sep = ", ")
                    elif input_stock=='3':
                        #End of loop
                        break
                    else:
                        print("That was not a valid entry! Try again.")
                except:
                    print("That was not a valid entry! Try again.")
            else:
                #End of loop
                break
######TASK 4: Add new book
def add_book():
    #Creating an empty list which is used to add the new book details
    empty_book=[]
    #Getting the book details from the user which is then added to the empty_book list
    empty_book.append(input("Enter The author's Name: "))
    empty_book.append(input("Enter the title of the book: "))
    empty_book.append(input("Enter the format of the book: "))
    empty_book.append(input("Enter the publisher of the book: "))
    while True:
        #Input validation to ensure the user enters a number
        try:
            empty_book.append(float(input("Enter the price of the book: ")))
        except ValueError:
            print("That entry is not valid. Please ensure you are entering a number")
            continue
        #If there was no ValueError, the user must have entered a number and the loop can end
        else:
            break
    while True:
        try:
            empty_book.append(int(input("Enter the stock level of the book: ")))
        except ValueError:
            print("That entry is not valid. Please ensure you are entering a number")
            continue
        else:
            break
    while True:
        #Checking the genre entered by the user matches those already on the system
        new_book_genre=input("Enter the genre of the book: ").lower()
        if new_book_genre in genre_list:
            #If the genre exists, add the input to the list and stop the loop
            empty_book.append(new_book_genre)
            break
        else:
            print("That genre doesn't exist on the system. Try again")

    #The AVG stock price is saved before the new book is added
    #As it is needed to calculate the difference
    current_avg_stock=avg_price()
    #The completed list is now added to the master list
    allbooks.append(empty_book)
    #The new average is saved to be used in a calculation
    new_avg_stock=avg_price()
    t=total_values()
    print('[New Total Num of Titles:'+str(t['titles'])+']')
    print('[New Total Num of Copies:',str(t['copies']),']')
    #This If statement finds the difference in the AVG price of the books by checking if the avg
    #has increased or decreased, it then subtracts the values
    if current_avg_stock>=new_avg_stock:
        #If the old average is higher than the new average,
        #then the average has decreased. The number is then rounded to 2 decimal points
        diff=round(current_avg_stock-new_avg_stock,2)
        print('AVG decreased by: '+str(diff))
        print('[New AVG:'+str(new_avg_stock)+']')
    else:
        diff=round(new_avg_stock-current_avg_stock,2)
        print('AVG increased by: '+str(diff))
        print('[New AVG:'+str(new_avg_stock)+']')

#####TASK 6: Return book items in alphabetical order
def alpha_order():
    print('[1]By Title [2]By Genre [3]Back')
    choice=input('Select: ')
    if int(choice)==1:
        #Sorting the list in alphabetical order by the title
        title_sort=sorted(allbooks, key = lambda book: book[1])
        for book in title_sort:
            #Printing the book list without brackets and seperating them with a ','
            print(*book, sep = ", ")
    elif int(choice)==2:
        #Sorting the list in alphabetical order by the genre
        genre_sort=sorted(allbooks, key = lambda book: book[-1])
        for book in genre_sort:
            print(*book, sep = ", ")
    else:
        pass

#Start of the main menu loop
while True:
    print("[Enter a key to goto its corresponding menu]")
    print("[1] All Books & Value")
    print("[2] Average Price Of Books In Stock")
    print("[3] Number Of Copies In Each Genre")
    print("[4] Check If A Book Is In Stock")
    print("[5] Add A New Book")
    print("[6] Sort By Title/Genre")
    print("[7] Graph")
    print("[8] Exit")
    select=input('Select: ')
    if select == "1":
        #Saving the list returned from the total_values function
        v=total_values()
        for book in allbooks:
            #Printing the books without the brackets and seperating them with a ','
            print(*book, sep = ", ")
        #Printing the report displaying the total books in stock, the copies and average price
        print('[TITLES: '+ str(v['titles'])+'] '+'[COPIES: '+str(v['copies'])+'] ' +' [TOTAL VALUE:'+"£{:,.2f}".format(float(price()))+']')
    if select == '2':
        print("[AVG PRICE:"+"£{:,.2f}".format(avg_price())+']')    

    elif select == '3':
        print('[Number of copies in each genre]:')
        #saving the result of the function to a variable
        genre_dict=genre_dic()
        for key, value in genre_dict.items():
            #Print the key in uppercase and seperated with ':'
            print(key.upper(), value, sep=': ')

    elif select=='4':
        print('[ID, AUTHOR, TITLE, FORMAT, PUBLISHER, COST, STOCK, GENRE]')
        for book in allbooks:
            #Printing the books without the brackets and seperating them with a ','
            print(*book, sep = ", ")
        stock()
    elif select == '5':
        add_book()  #Run the add book function
    elif select == '6':
        #Run the alphabetical order function
        alpha_order()
    elif select == '7': #Plotting Bar chart
        #Saving dictionary from function
        dictionary=genre_dic()
        keys = dictionary.keys()
        values = dictionary.values()
        plt.bar(keys, values)
        plt.show()
    elif select == '8':
        sure=input('Are you sure you want to exit? [Y]Yes [N]No: ')
        if sure.lower() =='y':
            break
        elif sure.lower() =='n':
            pass
        else:
            print('That was not a valid command, try again.')
