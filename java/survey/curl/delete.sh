selection="{.items[?(.metadata.name==\"${1}\")].spec.host}"
curl -i -v -X DELETE http://$(oc get routes --output jsonpath=${selection})/api/v1/survey/${2}
echo
