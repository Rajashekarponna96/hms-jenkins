
CREATE DATABASE hms;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

SET search_path TO hms, public;

DROP TABLE IF EXISTS hms.user_login;
CREATE TABLE IF NOT EXISTS hms.user_login(
 id SERIAL PRIMARY KEY,
 password text,
 user_name text,
 email_id text,
 mobile_number text,
 user_role text,
 user_type text,
 active boolean,
 created_time timestamp,
 modified_time timestamp,
 created_by text,
 modified_by text,
 reset_password boolean
);

DROP TABLE IF EXISTS hms.user_role;
CREATE TABLE IF NOT EXISTS hms.user_role(
 role_id SERIAL PRIMARY KEY,
 role text,
 name text,
 active boolean,
 created_time timestamp,
 modified_time timestamp,
 created_by text,
 modified_by text
);

DROP TABLE IF EXISTS hms.user_login_token;
CREATE TABLE IF NOT EXISTS hms.user_login_token(
 token_id uuid  DEFAULT uuid_generate_v4 () PRIMARY KEY,
 user_id text,
 email_token text,
 mobile_token text,
 email_id text,
 mobile_number text,
 expire_time_seconds int,
 active boolean,
 created_time timestamp,
 modified_time timestamp,
 created_by text,
 modified_by text,
 verfication_status text
);


DROP TABLE IF EXISTS hms.student_profile;
CREATE TABLE IF NOT EXISTS student_profile(
  id  SERIAL PRIMARY KEY,
  name varchar(50),
  first_name varchar(50) ,
  last_name varchar(50) ,
  father_name varchar(50) ,
  mother_name varchar(50) ,
  guardian_name varchar(50) ,
  email_id varchar(50),
  mobile_number varchar(15),
  alternate_mobile_number varchar(15),
  identity_type varchar(25),
  id_proof varchar(25),
  active boolean,
  address_one varchar(50),
  address_two varchar(50),
  land_mark varchar(50),
  city varchar(25),
  district varchar(25),
  state varchar(25),
  zip_code varchar(6),
  dob VARCHAR(50),
  gender varchar(10),
  marital_status varchar(20),
  physically_challenged char(1),
  created_by varchar(30),
  modified_by varchar(30),
  created_timestamp bigint,
  modified_timestamp bigint
  );

DROP TABLE IF EXISTS hms.student_qualification;
CREATE TABLE IF NOT EXISTS hms.student_qualification(
 std_qual_id SERIAL PRIMARY KEY,
 std_profile_id int,
 qualification text,
 university text,
 start_date Timestamp,
 end_date Timestamp,
 year_of_passing text,
 grade text,
 percentage text,
 active boolean,
  created_timestamp bigint,
  modified_timestamp bigint,
 created_by text,
 modified_by text
);

DROP TABLE IF EXISTS hms.student_admission_journey;
CREATE TABLE IF NOT EXISTS hms.student_admission_journey(
 std_profile_id int,
 admission_journey json,
 active boolean,
 created_time timestamp,
 modified_time timestamp,
 created_by text,
 modified_by text,
  PRIMARY KEY (std_profile_id)
);

DROP TABLE IF EXISTS hms.student_counsellor_questionnaire;
CREATE TABLE IF NOT EXISTS hms.student_counsellor_questionnaire(
 questionnaire_id SERIAL PRIMARY KEY,
 industry_type text,
 questionnaire text,
 active boolean,
  created_by varchar(30),
  modified_by varchar(30),
  created_timestamp bigint,
  modified_timestamp bigint
);

DROP TABLE IF EXISTS hms.student_counsellor_qty_report;
CREATE TABLE IF NOT EXISTS hms.student_counsellor_qty_report(
 qty_report_id SERIAL PRIMARY KEY,
 questionnaire_id int,
 questionnaire_answer text,
 active boolean,
  created_by varchar(30),
  modified_by varchar(30),
  created_timestamp bigint,
  modified_timestamp bigint
);

DROP TABLE IF EXISTS hms.college_admin_profile;
CREATE TABLE IF NOT EXISTS hms.college_admin_profile(
 coll_profile_id SERIAL PRIMARY KEY,
 profile_name text,
 name text,
 first_name text,
 last_name text,
 mobile_number text,
 email_id text,
 alt_mobile_number text,
 active boolean,
 created_time timestamp,
 modified_time timestamp,
 college_id int,
 created_by text,
 modified_by text
);

DROP TABLE IF EXISTS hms.courses;
CREATE TABLE IF NOT EXISTS hms.courses(
 course_id SERIAL PRIMARY KEY,
 category text,
 course_type text,
 level text ,
 course_name text,
 duration text,
 specialization json,
 term_years int,
 qualification_Criteria text,
 active boolean,
  created_timestamp bigint,
  modified_timestamp bigint,
 created_by text,
 modified_by text
);


DROP TABLE IF EXISTS hms.university;
CREATE TABLE IF NOT EXISTS hms.university(
 university_id SERIAL PRIMARY KEY,
 university_code text,
 name text,
 short_name text,
 logo bytea ,
 website text,
 contact_mail text,
 contact_mobile text,
 contact_person text,
 address_one text,
 address_two text,
 zip_code text,
 land_mark text,
 Geo_location text,
 GPS_location text,
 district text,
 city text,
 state text,
 university_location text,
 active boolean,
 created_time timestamp,
 modified_time timestamp,
 created_by text,
 modified_by text
);

DROP TABLE IF EXISTS hms.college;
CREATE TABLE IF NOT EXISTS hms.college(
 college_id SERIAL PRIMARY KEY,
 college_name text,
 short_name text,
 college_code text,
 establishment_year text,
 logo bytea,
 image bytea,
 address_one text,
 address_two text,
 zip_code text,
 land_mark text,
 Geo_location text,
 GPS_location text,
 district text,
 city text,
 state text,
 college_location text,
 contact_email text,
 contact_number text,
 contact_person text,
 website text,
 active char(1),
 created_timestamp bigint,
 modified_timestamp bigint,
 created_by text,
 modified_by text,
 land_line_number text,
 institute_type text,
 hostel_facility text,
 university_ids json,
 hostel_type text,
 hostel_capacity text,
 number_of_rooms text,
 annual_fees text,
 mess_charges text
);


DROP TABLE IF EXISTS hms.college_images;
CREATE TABLE IF NOT EXISTS hms.college_images(
 college_image_id SERIAL PRIMARY KEY,
 college_id int,
 college_name text,
 college_code text,
 image_urls json,
 active boolean,
 created_time timestamp,
 modified_time timestamp,
 created_by text,
 modified_by text
);

DROP TABLE IF EXISTS hms.college_course;
CREATE TABLE IF NOT EXISTS hms.college_course(
 college_course_id SERIAL PRIMARY KEY,
 college_university_id int,
 college_id int,
 course_id int,
 allotted_seats int,
 course_name text,
 fees int,
 fee_structure json,
 active boolean,
 created_timestamp bigint,
 modified_timestamp bigint,
 created_by text,
 modified_by text
);


DROP TABLE IF EXISTS hms.courses;
CREATE TABLE IF NOT EXISTS hms.courses(
 course_id SERIAL PRIMARY KEY,
 category text,
 course_type text,
 level text ,
 course_name text,
 duration text,
 specialization json,
 term_years int,
 qualification_Criteria text,
 active boolean,
 created_time timestamp,
 modified_time timestamp,
 created_by text,
 modified_by text,
 short_course_name text
);

DROP TABLE IF EXISTS hms.university;
CREATE TABLE IF NOT EXISTS hms.university(
 university_id SERIAL PRIMARY KEY,
 university_code text,
 name text,
 short_name text,
 logo bytea ,
 website text,
 contact_mail text,
 contact_mobile text,
 contact_person text,
 address_one text,
 address_two text,
 zip_code text,
 land_mark text,
 Geo_location text,
 GPS_location text,
 district text,
 city text,
 state text,
 university_location text,
 active boolean,
 created_time timestamp,
 modified_time timestamp,
 created_by text,
 modified_by text
);

DROP TABLE IF EXISTS hms.student_experience;
CREATE TABLE IF NOT EXISTS	hms.student_experience(
 std_expr_id SERIAL,
 std_profile_id int NOT NULL,
 title text,
 department text,
 employment_type text,
 company_name text,
 start_date Timestamp,
 end_date Timestamp,
 active boolean,
  created_timestamp bigint,
  modified_timestamp bigint,
 created_by text,
 modified_by text,
 PRIMARY KEY (std_expr_id)
);
ALTER table  hms.student_experience
    DROP CONSTRAINT IF EXISTS student_id_fk,
    ADD CONSTRAINT student_id_fk
    FOREIGN KEY (std_profile_id) REFERENCES hms.student_profile(id) ON DELETE RESTRICT;

DROP TABLE IF EXISTS hms.student_skills;
CREATE TABLE IF NOT EXISTS	hms.student_skills(
 std_skill_id SERIAL,
 std_profile_id int,
 category text,
 specialization text,
 level text,
 total_experience int,
 active char(1),
  created_timestamp bigint,
  modified_timestamp bigint,
 created_by text,
 modified_by text,
 CONSTRAINT student_experience_pkey PRIMARY KEY (std_expr_id),
     CONSTRAINT student_id_foreign_key FOREIGN KEY (std_profile_id)
         REFERENCES hms.student_profile (id) MATCH SIMPLE
         ON UPDATE NO ACTION
         ON DELETE NO ACTION
         NOT VALID

);

DROP TABLE IF EXISTS hms.student_applied_colleges;
CREATE TABLE IF NOT EXISTS	hms.student_applied_colleges(
 app_id SERIAL PRIMARY KEY,
 college_id int,
 college_code text,
 college_course_id int,
 student_id int,
 student_mobile_number text,
 student_email_id text,
 student_name text,
 applied_date timestamp,
 status text,
 active boolean,
  created_timestamp bigint,
  modified_timestamp bigint,
 created_by text,
 modified_by text
);

DROP TABLE IF EXISTS hms.student_shortlisted_colleges;
CREATE TABLE IF NOT EXISTS	hms.student_shortlisted_colleges(
 shrt_id SERIAL PRIMARY KEY,
 college_id int,
 college_code text,
 college_course_id int,
 student_id int,
 student_mobile_number text,
 student_email_id text,
 student_name text,
 active boolean,
  created_timestamp bigint,
  modified_timestamp bigint,
 created_by text,
 modified_by text
);

DROP TABLE IF EXISTS hms.college_credits;
CREATE TABLE IF NOT EXISTS hms.college_credits(
 credit_id SERIAL PRIMARY KEY,
 college_id int,
 college_code text,
 credit_points int,
 start_period timestamp,
 end_period timestamp,
 active boolean,
  created_timestamp bigint,
  modified_timestamp bigint,
 created_by text,
 modified_by text
);


 ALTER TABLE hms.college ADD COLUMN land_line_number TYPE text;
 ALTER TABLE hms.college ADD COLUMN institute_type TYPE text;
 ALTER TABLE hms.college ADD COLUMN hostel_facility TYPE text;
 ALTER TABLE hms.college ADD COLUMN university_ids TYPE json;
 ALTER TABLE hms.courses ADD COLUMN short_course_name TYPE text;

