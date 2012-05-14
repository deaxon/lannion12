# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended to check this file into your version control system.

ActiveRecord::Schema.define(:version => 20120511185810) do

  create_table "annees", :force => true do |t|
    t.integer  "year"
    t.boolean  "invisible_annee"
    t.datetime "created_at",      :null => false
    t.datetime "updated_at",      :null => false
  end

  create_table "projects", :force => true do |t|
    t.string   "projectName"
    t.text     "description"
    t.text     "shortDescription"
    t.string   "file"
    t.datetime "created_at",       :null => false
    t.datetime "updated_at",       :null => false
    t.integer  "annee_id"
    t.boolean  "invisible"
    t.string   "pdf"
  end

  create_table "semesters", :force => true do |t|
    t.integer  "teacher_id"
    t.integer  "project_id"
    t.string   "semesterTeacher"
    t.datetime "created_at",      :null => false
    t.datetime "updated_at",      :null => false
  end

  create_table "students", :force => true do |t|
    t.string   "studentName"
    t.string   "studentFirstName"
    t.string   "cip"
    t.boolean  "inactif"
    t.datetime "created_at",       :null => false
    t.datetime "updated_at",       :null => false
  end

  create_table "teachers", :force => true do |t|
    t.string   "teacherName"
    t.string   "teacherFirstName"
    t.string   "cipTeacher"
    t.boolean  "inactifTeacher"
    t.datetime "created_at",       :null => false
    t.datetime "updated_at",       :null => false
  end

  create_table "trimesters", :force => true do |t|
    t.integer  "student_id", :null => false
    t.integer  "project_id"
    t.string   "semester"
    t.datetime "created_at", :null => false
    t.datetime "updated_at", :null => false
  end

  create_table "uploads", :force => true do |t|
    t.integer  "parent_id"
    t.string   "file"
    t.integer  "attachable_id"
    t.string   "attachable_type"
    t.datetime "created_at",      :null => false
    t.datetime "updated_at",      :null => false
  end

  create_table "users", :force => true do |t|
    t.string   "name"
    t.string   "encrypted_password"
    t.string   "salt"
    t.datetime "created_at",         :null => false
    t.datetime "updated_at",         :null => false
  end

  add_index "users", ["name"], :name => "index_users_on_name", :unique => true

end
