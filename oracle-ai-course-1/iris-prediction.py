#!/usr/bin/env python
# coding: utf-8

# In[5]:


import pandas as pd
from sklearn.linear_model import LogisticRegression
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import accuracy_score


# In[13]:


iris_data = pd.read_csv('iris.csv')
iris_data.head()


# In[19]:


X = iris_data.drop(columns=['Id', 'Species'])
Y = iris_data['Species']


# In[21]:


print(Y)


# In[23]:


X_train, X_test, Y_train, Y_test = train_test_split( X, Y, test_size=0.2, random_state=50)


# In[25]:


print(X_train)


# In[27]:


scaler = StandardScaler()
X_train_scaled = scaler.fit_transform(X_train)
X_test_scaled = scaler.fit_transform(X_test)


# In[29]:


model= LogisticRegression()
model.fit(X_train_scaled, Y_train)


# In[31]:


Y_pred = model.predict(X_test_scaled)
accuracy = accuracy_score(Y_test, Y_pred)
print( "Accuracy:", accuracy) 


# In[33]:


print(Y_pred)


# In[37]:


new_data = np.array( [ [5.1,3.5,1.4,0.2], 
                       [6.3, 2.9, 5.6, 1.8],
                       [4.9, 3.0, 1.4, 0.2]] )


# In[39]:


new_data_scaled = scaler.transform(new_data)


# In[41]:


print(new_data_scaled)


# In[43]:


predictions = model.predict(new_data_scaled)


# In[45]:


print("Predictions:", predictions)


# In[47]:


myData = np.array( [ [5.9, 3.3, 1.4, 0.3] ])
myDataScaled = scaler.transform(myData)


# In[49]:


p = model.predict( myDataScaled)
print( ">>", p) 


# In[ ]:




