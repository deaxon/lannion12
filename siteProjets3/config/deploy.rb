#############################################################
#	Application
#############################################################

set :application, "siteProjets3"
set :deploy_to, "/Users/tictac/Sites/webapps/#{application}"
set :user, "gonr1001"
set :group, "ror"
set :domain, "tictacserver.gel.usherbrooke.ca"
set :database_username, "siteProjets"
set :deploy_via, :export

#############################################################
#	Settings
#############################################################
set :scm, :subversion
default_run_options[:pty] = true
#set :use_sudo, true


#############################################################
#	Servers
#############################################################

server "tictacserver.gel.usherbrooke.ca", :app, :web
#role :db, domain, :primary => true
#role :db,  "tictacServer.gel.usherbrooke.ca", :primary => true

#############################################################
#	Subversion
#############################################################
set :svn_username, "gonr1001"
set :repository,  "svn+ssh://#{svn_username}@svn.gel.usherbrooke.ca/grp/svn/lannion12/trunk/#{application}"
set :checkout, "export"

#############################################################
#	Passenger
#############################################################

role :app, "tictacserver.gel.usherbrooke.ca"
role :web, "tictacserver.gel.usherbrooke.ca"
#role :db,  "tictacserver.gel.usherbrooke.ca", :secondary => true

namespace :deploy do
  task :start, :roles => :app do
    run "touch #{current_release}/tmp/restart.txt"
  end

  task :stop, :roles => :app do
    # Do nothing.
  end

  desc "Restart Application"
  task :restart, :roles => :app do
    run "touch #{current_release}/tmp/restart.txt"
  end
end



# Or: `accurev`, `bzr`, `cvs`, `darcs`, `git`, `mercurial`, `perforce`, `subversion` or `none`

#role :web, "your web-server here"                          # Your HTTP server, Apache/etc
#role :app, "your app-server here"                          # This may be the same as your `Web` server
#role :db,  "your primary db-server here", :primary => true # This is where Rails migrations will run
#role :db,  "your slave db-server here"

# if you want to clean up old releases on each deploy uncomment this:
#after "deploy:restart", "deploy:cleanup"

#after 'deploy:update_code' do
#  run "cd /Users/exitlab/Sites/webapps/#{application}"; RAILS_ENV=production rake assets:precompile"
#end

# if you're still using the script/reaper helper you will need
# these http://github.com/rails/irs_process_scripts

# If you are using Passenger mod_rails uncomment this:
# namespace :deploy do
#   task :start do ; end
#   task :stop do ; end
#   task :restart, :roles => :app, :except => { :no_release => true } do
#     run "#{try_sudo} touch #{File.join(current_path,'tmp','restart.txt')}"
#   end
# end


