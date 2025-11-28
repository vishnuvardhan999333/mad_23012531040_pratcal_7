# SQLite and JSON Practical  
## Android Application – Fetch JSON from API, Store in SQLite, Display using RecyclerView

### **AIM**
Develop an Android application that retrieves person data in JSON format from an API,  
stores it in SQLite database, and displays it using RecyclerView.

---

## **Features**
✔ Fetch JSON from internet using `HttpURLConnection`  
✔ Parse JSON data  
✔ Store data in SQLite database  
✔ Display list using RecyclerView  
✔ Click button to view person details (MapActivity)  
✔ Uses Coroutines for background tasks  
✔ Clean UI with Material Design

---

## **Technologies Used**
- Kotlin  
- SQLite Database  
- RecyclerView  
- Coroutines  
- Material Components  
- HttpURLConnection  
- JSON Generator API (https://app.json-generator.com/)

---

## **JSON Fields**
Each Person contains:
- **id**
- **name** (first + last)
- **email**
- **phone**
- **address**
- **latitude**
- **longitude**

---

## **Project Structure**

```
app/src/main/java/<your_package>/
│── MainActivity.kt
│── MapActivity.kt
│── Person.kt
│── PersonAdapter.kt
│── HttpRequest.kt
│── DatabaseHelper.kt
│── PersonDbTableData.kt
│
app/src/main/res/layout/
│── activity_main.xml
│── activity_map.xml
│── person_item_view.xml
│
app/src/main/res/drawable/
│── person.xml
│── sync.xml
│── delete.xml
│── round_shape.xml
```

---

## **How the App Works**

### **1️⃣ Fetch JSON (MainActivity)**
- Press **Sync Button**
- Calls the API:
```
https://api.json-generator.com/templates/<templateID>/data?access_token=<token>
```
- Uses Coroutine + `HttpURLConnection`
- Converts InputStream to JSON string

### **2️⃣ Parse JSON**
JSON is parsed using `JSONArray` and `JSONObject`

### **3️⃣ Save in SQLite**
Stored using DatabaseHelper:
- insertPerson()
- deleteAllPersons()
- getAllPersons()

### **4️⃣ Show in RecyclerView**
Adapter loads list from SQLite and displays:
- Name  
- Phone  
- Email  
- Address  
- Map Button

### **5️⃣ MapActivity**
On button click, Person object is passed using `Serializable` and displayed.

---

## **Required Permissions**
Add in `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

---

## **Important Notes**
✔ Person class must implement Serializable  
✔ JSON Generator link must be valid  
✔ All layouts should match IDs used in Adapter  
✔ Coroutines must run on IO thread for network calls  
✔ SQLite database auto updates after sync

---

## **Sample JSON Format**
```json
{
  "id": "62efc7aee045a3e9a92e285",
  "email": "yesenia_yang@gnu.ac.in",
  "phone": "+919783385148",
  "profile": {
    "name": "Yesenia Yang",
    "address": "16 Sackett Street, Courtland, Michigan",
    "location": {
      "lat": 19.146864,
      "long": 72.081184
    }
  }
}
```

---

## **Output Screens**
- Light Theme List  
- Dark Theme List  
- JSON Generator Code  
- SQLite Helper Code  
- Person Adapter  
- Map Activity  
- RecyclerView Output  

<img width="1330" height="692" alt="image" src="https://github.com/user-attachments/assets/6229ba70-0743-4a1d-b3e8-fba99fbbce02" />

---

## **Conclusion**
This practical demonstrates:
- How to fetch data from API  
- How to parse JSON  
- How to store data locally  
- How to display data using RecyclerView  
- How to use Android Coroutines and SQLite effectively  

---

### **Created By:**  
**Vishnu Vardhan (23012531040)**  
B.Tech AI & ML  
U.V. Patel College of Engineering  
