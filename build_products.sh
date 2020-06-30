#! /usr/bin/env bash

base_dir="iris-spl"
products_dir="iris_products"
current_dir=`pwd | xargs basename`
path=`pwd | xargs dirname`
output_log=/dev/null



for input in ./src-gen/irisdeltaj/*/ ; do
    product_name=`basename $input`
    mvn clean package -Dmaven.test.skip=true -Diris.product=$product_name
    if [ $? -ne 0 ]; then
	echo "Error building product $product_name, aborting!!" 
	exit 1
    fi
done
