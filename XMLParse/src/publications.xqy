(:query 1:)

for $x in doc("dblp-soc-papers.xml")/dblp/article
where fn:contains($x/title, 'Software Engineering')
return $x/title


(:query 2:)
(:
for $x in doc("dblp-soc-papers.xml")/dblp/article
where $x/author = 'Jia Zhang'
where $x/year = '2018'
return $x/title
:)

(:query 3:)
(:loop through all dblp, where author occurences >10 :)
(:
for $x in doc("dblp-soc-papers.xml")/dblp/inproceedings
let $count := 0
let $auth := $x/author
for $z in doc("dblp-soc-papers.xml")/dblp/inproceedings/author
where $auth = $z
let $count := $count + 1
return if ($count > 9)
then $auth
else ()
:)


(:query 4:)
(:declare paper name here as global variable:)
(:
declare variable $papername := 'Ontology Classification for Semantic-Web-Based Software Engineering.';
for $x in doc("dblp-soc-papers.xml")/dblp/article
where $x/title = $papername
return $x
:)
