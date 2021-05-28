<template>
  <div class="AddCar">
    <v-form>
    <v-container>
        <v-row>
           <v-col cols="8"
                lg="4"
                md="6"
                xs="12">

                <v-card outlined  >
                <v-card-title> Add a Car </v-card-title>
                <v-card-text>
                    <v-text-field v-model="car.name" label="Denumire" placeholder="String"></v-text-field>
                    <v-text-field v-model="car.yearOfFabrication" label="Anul Fabricatiei" placeholder="Int"></v-text-field>
                    <v-text-field v-model="car.coverURL" label="CoverURL" placeholder="https://"></v-text-field>
                    <v-text-field v-model="car.price" label="Price/Day" placeholder="Int $$$"></v-text-field>
                </v-card-text>
            <v-card-actions>
              <v-btn rounded color="primary" dark  href="/Cars" @click="saveCar"> Save car </v-btn>     
            </v-card-actions>
            </v-card>
            </v-col>
          
          <v-col cols="8">
            
              <v-col
                cols="12"
                lg="4"
                md="6"
                xs="12"
              >
                <v-card >
                  <v-img
                    :src="car.coverURL"
                    height="200px"
                  ></v-img>

                  <v-card-title> Denumire: {{ car.name }} </v-card-title>

                  <v-card-subtitle> yearOfFabrication: {{ car.yearOfFabrication }} </v-card-subtitle>

                  <v-card-subtitle> Price/Day: {{ car.price }} </v-card-subtitle>
                </v-card>
                </v-col>
            
          </v-col>
        </v-row>
      </v-container>
  </v-form>
  </div>  
</template>

<script>
import axios from 'axios';
export default {
  name: "AddCar",
  data() {
    return {
      car: {
        name: "",
        yearOfFabrication: "",
        price: "",
        coverURL: "",
      },
    };
  },
  methods: {
      saveCar(){
          axios.post("http://localhost:8080/api/Cars", this.car).then(() => {
        })
        .catch((err) => {});
      }
  },
};
</script>

