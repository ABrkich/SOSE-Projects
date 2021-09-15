(:query 1:)
(:
for $x in doc("dblp-soc-papers.xml")/dblp/article
where fn:contains($x/title, 'Software Engineering')
return $x/title
:)

(:query 2:)
(:
for $x in doc("dblp-soc-papers.xml")/dblp/article
where $x/author = 'Jia Zhang'
where $x/year = '2018'
return $x/title
:)

(:query 3:)
(:loop through all dblp, where author occurences >10 :)

(: loop through all articles, make an array of author counts, then do
 return author counts where count > 10 where title 'SOSE':)

let $f2 := "hi"
(: let $f3 :=  fn:string-join(($sequence, "hello mom"), " ") :)
(: return $f3 :)

for $x in doc("dblp-soc-papers.xml")/dblp/inproceedings
let $c := fn:concat($f2, $x/author)
let $f2 := fn:string-join(($f2, $x/author), " ")
return $f2


(:
for $x in doc("dblp-soc-papers.xml")/dblp/inproceedings
where fn:contains($x/title, 'Software Engineering')
return $x/author
:)



(:query 4:)
(:declare paper name here as global variable:)
(:
declare variable $papername := 'Ontology Classification for Semantic-Web-Based Software Engineering.';
for $x in doc("dblp-soc-papers.xml")/dblp/article
where $x/title = $papername
return $x
:)
